import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class newVoter extends JFrame {
    private JPanel mainPanel;
    private JTextField nameTF;
    private JTextField addressTF;
    private JButton registerButton;
    private JLabel idLabel;
    private JPasswordField passwordField1;

    newVoter(Election election){
        setContentPane(mainPanel);
        setTitle("Login");
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setSize(300,200);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        int id=globals.createNewID(globals.votersIDs);
        idLabel.setText(Integer.toString(id));
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Voter voter=new Voter(id,nameTF.getText(),addressTF.getText(),false,passwordField1.getText());
                voter.setElection(election);
                election.addToAppendingVoters(voter);
                JOptionPane.showConfirmDialog(null,"Voter ID pending approval by admin");
                DB.updateAll();

                dispose();
            }
        });
    }
}
