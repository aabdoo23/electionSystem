import java.time.LocalDate;
import java.util.LinkedList;

public class Election {
    private int ID,totalVotes=0;
    private LocalDate electionDate;
    public LinkedList<Candidate>candidateLinkedList=new LinkedList<>();
    public LinkedList<Voter>appendingVoterLinkedList=new LinkedList<>();
    public LinkedList<Voter>confirmedVoterLinkedList=new LinkedList<>();
    public LinkedList<Voter>votedVotersList=new LinkedList<>();
    public LinkedList<Vote>votesLinkedList=new LinkedList<>();

    Election(){}
    Election(int id, LocalDate electionDate){
        this.ID=id;
        this.electionDate=electionDate;
    }
    Election(int id,int totalVotes, LocalDate electionDate){
        this.ID=id;
        this.totalVotes=totalVotes;
        this.electionDate=electionDate;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    public void setElectionDate(LocalDate electionDate) {
        this.electionDate = electionDate;
    }
    public void addToCandidates(Candidate candidate){
        candidateLinkedList.add(candidate);
    }
    public void addToConfirmedVoters(Voter voter){
        confirmedVoterLinkedList.add(voter);
    }
    public void addToAppendingVoters(Voter voter){
        appendingVoterLinkedList.add(voter);
    }

    public void addToVotedVoters(Voter voter){votedVotersList.add(voter);}
    public void addToVotes(Vote vote){votesLinkedList.add(vote);}
    public LinkedList<Candidate> getCandidateLinkedList() {
        return candidateLinkedList;
    }
    public int getID() {
        return ID;
    }
    public LinkedList<Vote> getVotesLinkedList() {
        return votesLinkedList;
    }
    public LinkedList<Voter> getVotedVotersList() {
        return votedVotersList;
    }
    public LinkedList<Voter> getConfirmedVoterLinkedList() {
        return confirmedVoterLinkedList;
    }
    public LinkedList<Voter> getAppendingVoterLinkedList() {
        return appendingVoterLinkedList;
    }
    public LocalDate getElectionDate() {
        return electionDate;
    }
    public int getTotalVotes() {
        return totalVotes;
    }
    public void incTotalVotes() {
        this.totalVotes++;
    }

    public void setAppendingVoterLinkedList(LinkedList<Voter> appendingVoterLinkedList) {
        this.appendingVoterLinkedList = appendingVoterLinkedList;
    }
    public void setCandidateLinkedList(LinkedList<Candidate> candidateLinkedList) {
        this.candidateLinkedList = candidateLinkedList;
    }
    public void setConfirmedVoterLinkedList(LinkedList<Voter> confirmedVoterLinkedList) {
        this.confirmedVoterLinkedList = confirmedVoterLinkedList;
    }
    public void setTotalVotes(int totalVotes) {
        this.totalVotes = totalVotes;
    }
    public void setVotedVotersList(LinkedList<Voter> votedVotersList) {
        this.votedVotersList = votedVotersList;
    }
    public void setVotesLinkedList(LinkedList<Vote> votesLinkedList) {
        this.votesLinkedList = votesLinkedList;
    }


    public String [] getResultSA(){
        String [] Results = new String[candidateLinkedList.size()];
        if (getTotalVotes() != 0) {
            if(candidateLinkedList!=null&&candidateLinkedList.size()!=0) {
                int i = 0;
                for (Candidate candidate : candidateLinkedList) {
                    Results[i] = ("Candidate ID: "+Integer.toString(candidate.getID()) +"Candidate name: "+candidate.get_name()+ " Number of votes: " + Integer.toString(candidate.getVotesReceived() / getTotalVotes()));
                    i++;
                }
            }
        }
        return Results;
    }
    public Candidate calculateWinner(){
        int maxi=0;
        Candidate winnerID = null;
        for (Candidate candidate:candidateLinkedList){
            if(candidate.getVotesReceived()>maxi){
                maxi=candidate.getVotesReceived();
                winnerID= candidate;
            }
        }
        return winnerID;
    }

    public String toStringed() {
        return  "ID=" + ID +
                ", electionDate=" + electionDate ;
    }
    @Override
    public String toString() {
        return "Election{" +
                "ID=" + ID +
                ", \nelectionDate=" + electionDate +
                ", \ncandidateLinkedList=" + candidateLinkedList +
                ", \nappendingVoterLinkedList=" + appendingVoterLinkedList +
                ", \nconfirmedVoterLinkedList=" + confirmedVoterLinkedList +
                ", \nvotedVotersList=" + votedVotersList +
                ", \nvotesLinkedList=" + votesLinkedList +
                '}';
    }
}
