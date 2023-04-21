import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class AdminForm extends JFrame {
    private JPanel mainPanel;
    private JTabbedPane tabbedPane1;
    private JList<String>candidateList;
    private JList<String> appending;
    private JList<String> accepted;
    private JButton rejectButton;
    private JButton acceptButton;
    private JButton dropAcceptedBTN;
    private JButton closeButton;
    private JButton candidateDropBTN;

    private JButton selectButton;
    private JList<String> resultsList;
    private JLabel winnerLabel;
    private JButton newElectionBtn;
    private JButton newCandidateButton;
    private JButton refreshButton;
    private JList<String> electionsList;
    private JComboBox<String> electionsCB;
    private JButton deleteElectionButton;
    Election election=null;
    void updateDisplay(){
        if(electionsCB.getSelectedIndex()!=-1)
            election=globals.electionsLinkedList.get(electionsCB.getSelectedIndex());
        globals.customMakeList(globals.electionsLinkedList,electionsCB);
        globals.makeList(globals.electionsLinkedList,electionsList);
        if (election!=null){
            globals.makeList(election.getCandidateLinkedList(),candidateList);
            globals.makeList(election.getAppendingVoterLinkedList(),appending);
            globals.makeList(election.getConfirmedVoterLinkedList(),accepted);
            globals.makeList(election.getResultSA(),resultsList);
        }
        if(election!=null&&election.calculateWinner()!=null)
            winnerLabel.setText(election.calculateWinner().toString());
        else winnerLabel.setText("");
        DB.updateAll();

    }
    AdminForm(){
        updateDisplay();
        setContentPane(mainPanel);
        setTitle("Admin panel");
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setSize(500,500);
//        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                election=globals.electionsLinkedList.get(electionsCB.getSelectedIndex());
                updateDisplay();
            }
        });
        if(election!=null)
            winnerLabel.setText(election.calculateWinner().toString());

        candidateDropBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                election.candidateLinkedList.remove(candidateList.getSelectedIndex());
                updateDisplay();
            }
        });

        rejectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                election.appendingVoterLinkedList.remove(appending.getSelectedIndex());
                globals.votersIDs[election.appendingVoterLinkedList.get(appending.getSelectedIndex()).getID()]=false;
                updateDisplay();
            }
        });

        acceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                election.appendingVoterLinkedList.get(appending.getSelectedIndex()).setPending(true);
                election.confirmedVoterLinkedList.add(election.appendingVoterLinkedList.get(appending.getSelectedIndex()));
                election.appendingVoterLinkedList.get(appending.getSelectedIndex()).setPending(false);
                election.appendingVoterLinkedList.remove(appending.getSelectedIndex());
                updateDisplay();
            }
        });

        dropAcceptedBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                election.confirmedVoterLinkedList.remove(accepted.getSelectedIndex());
                globals.votersIDs[election.confirmedVoterLinkedList.get(accepted.getSelectedIndex()).getID()]=false;

                updateDisplay();
            }
        });
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        newElectionBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Election election1=new Election(globals.createNewID(globals.electionIDs), LocalDate.now());
                globals.electionsLinkedList.add(election1);
                updateDisplay();
            }
        });
        newCandidateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new newCandidate();
                updateDisplay();
            }
        });
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateDisplay();
            }
        });
        deleteElectionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                globals.electionsLinkedList.remove(electionsList.getSelectedIndex());
                globals.electionIDs[election.getID()]=false;
                updateDisplay();
            }
        });
    }
}
