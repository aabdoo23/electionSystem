public class Candidate {
    private int ID,votesReceived=0;
    private String party,name;
    private Election election=new Election();
    //constructors
    Candidate(){}
    Candidate(int id,String name,String party){
        this.ID=id;

        this.party=party;
        this.name=name;
    }

    //setters
    public void setID(int ID) {
        this.ID = ID;
    }

    public void setElection(Election election) {
        this.election = election;
    }

    public Election getElection() {
        return election;
    }

    public void incVotesReceived() {
        this.votesReceived++;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setParty(String party) {
        this.party = party;
    }
    //getters
    public int getID() {
        return ID;
    }
    public int getVotesReceived() {
        return votesReceived;
    }
    public String get_name() {
        return name;
    }
    public String getParty() {
        return party;
    }

    @Override
    public String toString() {
        return "Candidate{" +
                "ID= " + ID +
                ", name= " + name  +
                ", votesReceived= " + votesReceived +
                ", party= " + party +
                '}';
    }

    public void setVotesReceived(int votesReceived) {
        this.votesReceived=votesReceived;
    }
}
