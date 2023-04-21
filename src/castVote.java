import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
public class castVote extends JFrame {
    private JLabel labelID;
    private JLabel labelName;
    private JPanel mainPanel;
    private JButton voteButton;
    private JComboBox<String> candidatesCB;

    castVote(Voter voter,Election election){
        labelID.setText(Integer.toString(voter.getID()));
        labelName.setText(voter.get_name());
        globals.makeList(election.getCandidateLinkedList(),candidatesCB);
        setContentPane(mainPanel);
        setTitle("Cast vote");
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setSize(300,150);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        voteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Candidate candidate=election.getCandidateLinkedList().get(candidatesCB.getSelectedIndex());
                int confirmed = JOptionPane.showConfirmDialog(null, "Are you sure you want to cast your vote to"+candidate.get_name()+"?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (confirmed == JOptionPane.NO_OPTION) {
                    dispose();
                }
                else{
                    Vote vote=new Vote(voter.getID(),candidate.getID(), LocalTime.now());
                    vote.setElection(election);
                    election.addToVotes(vote);
                    voter.setHasVoted(true);
                    candidate.incVotesReceived();
                    election.incTotalVotes();
                    JOptionPane.showConfirmDialog(null,"Vote registered.");
                    voter.setVote(vote);
                    DB.updateAll();
                    dispose();
                }
            }
        });
    }
}
