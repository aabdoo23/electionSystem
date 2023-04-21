import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class voterMenu extends JFrame {
    private JButton voteButton;
    private JLabel IDLabel;
    private JLabel nameLabel;
    private JLabel addressLabel;
    private JLabel pendingLabel;
    private JPanel mainPanel;
    private JLabel votedLabel;

    voterMenu(Voter voter,Election election){
        setContentPane(mainPanel);
        setTitle("Voter");
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setSize(300,300);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        IDLabel.setText(Integer.toString(voter.getID()));
        nameLabel.setText(voter.get_name());
        addressLabel.setText(voter.getAddress());
        if(voter.isPending()) {
            pendingLabel.setText("Yes");
            voteButton.setEnabled(false);
        }
        else pendingLabel.setText("No");
        if(voter.isHasVoted()){
            votedLabel.setText("Yes");
            voteButton.setEnabled(false);
        }
        else{votedLabel.setText("No");}

        voteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new castVote(voter,election);
                dispose();
            }
        });
    }
}
