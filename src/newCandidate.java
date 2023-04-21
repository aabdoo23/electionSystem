import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class newCandidate extends JFrame{
    private JPanel mainPanel;
    private JTextField nameTF;
    private JComboBox<String> comboBox1;
    private JLabel idLabel;
    private JButton registerButton;
    private JComboBox<String> electionCB;
    Election election;
    newCandidate(){
        setContentPane(mainPanel);
        setTitle("New candidate");
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setSize(300,300);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        globals.customMakeList(globals.electionsLinkedList,electionCB);
        int id=globals.createNewID(globals.candidateIDs);
        idLabel.setText(Integer.toString(id));
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Candidate candidate=new Candidate(id,nameTF.getText(),comboBox1.getSelectedItem().toString());
                election=globals.electionsLinkedList.get(electionCB.getSelectedIndex());
                candidate.setElection(election);
                election.addToCandidates(candidate);
                JOptionPane.showConfirmDialog(null,"Candidate registered");
                DB.updateAll();

                dispose();

            }
        });
    }
}
