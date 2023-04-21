import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class mainMenu extends JFrame {
    private JPanel mainPanel;
    private JButton newVoterButton;
    private JButton loginButton;
    private JButton adminButton;
    private JComboBox<String> electionsCB;
    private JButton refreshButton;

    Election currElection(){
        return globals.electionsLinkedList.get(electionsCB.getSelectedIndex());
    }
    public mainMenu() {
        globals.customMakeList(globals.electionsLinkedList,electionsCB);
        setContentPane(mainPanel);
        setTitle("Main menu");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400,200);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        newVoterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currElection()==null){
                    JOptionPane.showMessageDialog(null,"Invalid choice","INVALID",JOptionPane.ERROR_MESSAGE);
                }
                else new newVoter(currElection());
                globals.customMakeList(globals.electionsLinkedList,electionsCB);

            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currElection()==null||electionsCB.getSelectedIndex()==-1){
                    JOptionPane.showMessageDialog(null,"Invalid choice","INVALID",JOptionPane.ERROR_MESSAGE);
                }
                else new voterLogin(currElection());
                globals.customMakeList(globals.electionsLinkedList,electionsCB);

            }
        });

        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AdminForm();
                globals.customMakeList(globals.electionsLinkedList,electionsCB);

            }
        });
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                globals.customMakeList(globals.electionsLinkedList,electionsCB);
            }
        });
    }


}
