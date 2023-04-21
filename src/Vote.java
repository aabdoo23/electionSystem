import java.time.LocalTime;
public class Vote {
    private int ID,voterID,candidateID;
    private LocalTime voteCastTime;
    Election election;
    Vote(){}
    Vote(int voterID,int candidateID,LocalTime voteCastTime){
        this.ID=globals.createNewID(globals.voteIDs);
        this.voterID=voterID;
        this.candidateID=candidateID;
        this.voteCastTime=voteCastTime;
    }
    Vote(int ID,int voterID,int candidateID,LocalTime voteCastTime){
        this.ID=ID;
        this.voterID=voterID;
        this.candidateID=candidateID;
        this.voteCastTime=voteCastTime;
    }
    public void setID(int ID) {
        this.ID = ID;
    }
    public void setVoteCastTime(LocalTime voteCastTime) {
        this.voteCastTime = voteCastTime;
    }
    public void setCandidateID(int candidateID) {
        this.candidateID = candidateID;
    }
    public void setVoterID(int voterID) {
        this.voterID = voterID;
    }

    public int getID() {
        return ID;
    }

    public void setElection(Election election) {
        this.election = election;
    }

    public Election getElection() {
        return election;
    }

    public int getCandidateID() {
        return candidateID;
    }
    public int getVoterID() {
        return voterID;
    }
    public LocalTime getVoteCastTime() {
        return voteCastTime;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "ID=" + ID +
                ", voterID=" + voterID +
                ", candidateID=" + candidateID +
                ", voteCastTime=" + voteCastTime +
                '}';
    }
}
