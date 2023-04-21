import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class voterLogin extends JFrame {
    private JPanel mainPanel;
    private JTextField IDtf;
    private JPasswordField Nametf;
    private JButton loginButton;

    voterLogin(Election election) {
        setContentPane(mainPanel);
        setTitle("Login");
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setSize(300, 150);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Voter voterer : election.appendingVoterLinkedList) {
                    if (voterer.getID() == Integer.parseInt(IDtf.getText()) && Objects.equals(voterer.getPassword(), Nametf.getText())) {
                        new voterMenu(voterer,election);
                        dispose();
                        return;
                    }
                }
                for (Voter voter : election.getConfirmedVoterLinkedList()) {
                    if (voter.getID() == Integer.parseInt(IDtf.getText()) && Objects.equals(voter.getPassword(), Nametf.getText())) {
                        new voterMenu(voter,election);
                        dispose();
                        return;
                    }
                }

                JOptionPane.showMessageDialog(null, "Error: Credentials don't match", "Error", JOptionPane.ERROR_MESSAGE);

            }
        });
    }
}
