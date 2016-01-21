package it.polimi.diceH2020.WS4GSPN.controller;

import java.math.BigDecimal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.polimi.diceH2020.WS4GSPN.model.SimulationsManager;
import it.polimi.diceH2020.WS4GSPN.model.Simulations_class;
import it.polimi.diceH2020.WS4GSPN.service.DiceService;

@Controller
@RequestMapping("/V10")
@EnableAsync
public class FifoV10Controller {
		
		@Autowired	
		DiceService ds;
	
	 	@ModelAttribute("sim_manager")
	 	public SimulationsManager createSim_manager(){ 
	     return new SimulationsManager();
	 	}
		
	 	@ModelAttribute("sim_class") 
	 	public Simulations_class getSimClass() {     
	 		return new Simulations_class(); 
	 	}
	 	
	 	@RequestMapping(value="/error", method=RequestMethod.GET)
	    public String showSimulationsManagerForm(){ 
	    	return "error";
	    }
	 	
	 	@RequestMapping(value="/simulations", method=RequestMethod.GET)
	    public String showSimulationsManagerForm(@ModelAttribute("sim_manager") SimulationsManager sim_manager){ 
	 		sim_manager.setClass_number(1);
	    	return "set_simulations_form_V10";
	    }
	 	
	 	@RequestMapping(value="/simulations", method=RequestMethod.POST)
	 	public String checkSimulationsManagerForm(@Valid @ModelAttribute("sim_class") Simulations_class sim_class,@Valid @ModelAttribute("sim_manager") SimulationsManager sim_manager, BindingResult bindingResult) {
	        if (bindingResult.hasErrors()) {
	            return "set_simulations_form_V10"; 
	        }
	        fixParams(sim_manager);
	        fixClassParams(sim_class);
        	setRates(sim_class);
	    	sim_class.setId(0); //TODO check
		    sim_manager.getClassList().add(0,sim_class);
	        setUpSize(sim_manager);
	        sim_manager.setModel("V10");
    		ds.simulationV10(sim_manager);
    		return "redirect:/listV10";
    		}
	    /*
	    @RequestMapping(value="createSimulationsV10", method=RequestMethod.POST)
	    public String runSimulations(@ModelAttribute("sim_manager") SimulationsManager sim_manager) {
	    		
	    		return "runSimulations";
	    }*/
	    
	    private void setUpSize(SimulationsManager sim_manager){
	    	int size = 1;
	    	if(sim_manager.getSize()==1){
	    		for (int i = 0; i < sim_manager.getClassList().size(); i++) {
	    			int maxNumCores = sim_manager.getClassList().get(i).getMaxNumCores();
    				int minCores = sim_manager.getClassList().get(i).getMinNumCores();
    				int stepCores = sim_manager.getStepCores();
    				int maxNumUsers = sim_manager.getClassList().get(i).getMaxNumUsers();
    				int minNumUsers = sim_manager.getClassList().get(i).getMinNumUsers();
    				int stepUsrs = sim_manager.getStepUsrs();
    				
    				size *= ((int)Math.floor((maxNumCores-minCores)/stepCores)+1)*((int)Math.floor((maxNumUsers-minNumUsers)/stepUsrs)+1);
	    		}
    			sim_manager.setSize(size);
    		}
	    }
	    
	    private void fixParams(SimulationsManager sim_manager){
	    	if(sim_manager.getMinNumCores()!=null && sim_manager.getMaxNumCores()!=null){
	    		if(sim_manager.getMinNumCores() > sim_manager.getMaxNumCores()){
	    			sim_manager.setMinNumCores(sim_manager.getMaxNumCores());
	    		}
	    	}
	    	
	    	if(sim_manager.getMinNumUsers()!=null && sim_manager.getMaxNumUsers()!=null){
	    		if(sim_manager.getMinNumUsers() > sim_manager.getMaxNumUsers()){
	    			sim_manager.setMinNumUsers(sim_manager.getMaxNumUsers());
	    		}
	    	}
	    	if(sim_manager.getMinNumOfBatch()==null||sim_manager.getMaxNumOfBatch()==null)
        	{
        		sim_manager.setMinNumOfBatch(0);
        		sim_manager.setMaxNumOfBatch(0);
        	}
	    }
	    private void fixClassParams(Simulations_class sim_class){
	    	if(sim_class.getMinNumCores()!=null && sim_class.getMaxNumCores()!=null){
	    		if(sim_class.getMinNumCores() > sim_class.getMaxNumCores()){
	    			sim_class.setMinNumCores(sim_class.getMaxNumCores());
	    		}
	    	}
	    	
	    	if(sim_class.getMinNumUsers()!=null && sim_class.getMaxNumUsers()!=null){
	    		if(sim_class.getMinNumUsers() > sim_class.getMaxNumUsers()){
	    			sim_class.setMinNumUsers(sim_class.getMaxNumUsers());
	    		}
	    	}
	    }
	    
	    private void setRates(Simulations_class sim_class){
	    	sim_class.setMapRate(round((double)1/sim_class.getMapTime(),7,BigDecimal.ROUND_HALF_UP));
	    	sim_class.setReduceRate(round((double)1/sim_class.getReduceTime(),7,BigDecimal.ROUND_HALF_UP));
	    	sim_class.setThinkRate(round((double)1/sim_class.getThinkTime(),7,BigDecimal.ROUND_HALF_UP));
	    }
	    
	    /**
	     * Round doubles without losing precision. 
	     * @param unrounded
	     * @param precision
	     * @param roundingMode
	     * @return
	     */
	    public static double round(double unrounded, int precision, int roundingMode)
	    {
	        BigDecimal bd = new BigDecimal(unrounded);
	        BigDecimal rounded = bd.setScale(precision, roundingMode);
	        return rounded.doubleValue();
	    }
}
