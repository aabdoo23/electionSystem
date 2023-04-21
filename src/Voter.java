public class Voter {
    private int ID;
    private String name,address,password;
    private boolean hasVoted,pending;
    Election election=new Election();
    Vote vote;
    Voter(){}
    Voter(int ID,String name,String address,boolean hasVoted,String password){
        this.ID=ID;
        this.name=name;
        this.address=address;
        this.hasVoted=hasVoted;
        this.password=password;
        pending=true;
    }
    Voter(int ID,String name,String address,boolean hasVoted,String password,boolean pending){
        this.ID=ID;
        this.name=name;
        this.address=address;
        this.hasVoted=hasVoted;
        this.password=password;
        this.pending=pending;
    }

    public void setElection(Election election) {
        this.election = election;
    }

    public Election getElection() {
        return election;
    }

    public void setPending(boolean pending) {
        this.pending = pending;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setHasVoted(boolean hasVoted) {
        this.hasVoted = hasVoted;
    }
    public void setID(int ID) {
        this.ID = ID;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setVote(Vote vote) {
        this.vote = vote;
    }

    public Vote getVote() {
        return vote;
    }

    public int getID() {
        return ID;
    }
    public boolean isPending() {
        return pending;
    }
    public String getAddress() {
        return address;
    }
    public String get_name() {
        return name;
    }
    public boolean isHasVoted() {
        return hasVoted;
    }
    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Voter{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", hasVoted=" + hasVoted +
                '}';
    }
}
