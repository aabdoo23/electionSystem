import java.util.Arrays;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        Arrays.fill(globals.electionIDs,false);
        Arrays.fill(globals.votersIDs,false);
        Arrays.fill(globals.voteIDs,false);
        Arrays.fill(globals.candidateIDs,false);
        new DB();
        globals.electionsLinkedList=DB.getAllElections();
        for (Election election:globals.electionsLinkedList){
            LinkedList<Voter>all=DB.getAllVoters(election.getID());
            for (Voter voter:all){
                if (voter.isPending()) {
                    election.addToAppendingVoters(voter);
                } else if (voter.isHasVoted()) {
                    election.addToConfirmedVoters(voter);
                    election.addToVotedVoters(voter);
                } else {
                    election.addToConfirmedVoters(voter);
                }
            }
            election.setCandidateLinkedList(DB.getAllCandidates(election.getID()));
            election.setVotesLinkedList(DB.getAllVotes(election.getID()));
        }
        new mainMenu();
    }
}