package it.polimi.diceH2020.WS4GSPN.utility;

import java.io.File;
import java.util.List;

/**
 * recycled pojo used to handle ssh connection between the web server and the simulator
 */
public  class SshConnector {

    // this object runs bash-script on AMPL server
    private ExecSSH newExecSSH;

    // this object uploads files on AMPL server
    private ScpTo newScpTo;

    // this block downloads logs and results of AMPL
    private ScpFrom newScpFrom;
    
     
    public SshConnector(String host, String user, String password, String pubKeyFile, String setKnownHosts) {
        newExecSSH = new ExecSSH(host, user, password,pubKeyFile,setKnownHosts);
        newScpTo = new ScpTo(host, user, password,pubKeyFile,setKnownHosts);
        newScpFrom = new ScpFrom(host, user, password,pubKeyFile,setKnownHosts);
    }
    
    /**
     * Used to copy local file in the remote directory
     * @param localFile
     * @param remoteFile
     * @throws Exception
     */
    public void sendFile(String localFile, String remoteFile) throws Exception {
        newScpTo.sendfile(localFile, remoteFile);
        fixFile(new File(remoteFile).getParent(), new File(remoteFile).getName());
        //System.out.println("file " +localFile+ " sended");
    }
    
    /**
     * Used to execute a bash command 
     * @param command
     * @return
     * @throws Exception
     */
    public List<String> exec(String command) throws Exception {
         return newExecSSH.mainExec(command);
    }
    
    /**
     * Used to receive solution's file
     * @param localFile where to save locally the file
     * @param remoteFile the file to be saved
     * @throws Exception
     */
    public void receiveFile(String localFile, String remoteFile) throws Exception {
        newScpFrom.receivefile(localFile, remoteFile);
    }
    private void fixFile(String folder, String file) throws Exception {
        exec(String.format("cd %1$s && tr -d '\r' < %2$s > %2$s-bak && mv %2$s-bak %2$s",
                        folder,
                        file));
    }
}