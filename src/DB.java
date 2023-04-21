import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;

public class DB {
    private static Connection connection;
    String url = "jdbc:mysql://localhost:3306/electionsw";
    String username = "root";
    String pass = "";

    public DB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, pass);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
    }

    public static void clearVoteTable() {
        try {
            Statement statement = connection.createStatement();
            statement.execute("SET FOREIGN_KEY_CHECKS=0");

            statement.executeUpdate("TRUNCATE TABLE vote");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void clearElectionsTable() {
        try {
            Statement stmt = connection.createStatement();
            stmt.execute("SET FOREIGN_KEY_CHECKS=0");
            String sql = "TRUNCATE TABLE election";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void clearVoterTable() {
        try {
            Statement stmt = connection.createStatement();
            stmt.execute("SET FOREIGN_KEY_CHECKS=0");

            String sql = "TRUNCATE TABLE voter";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void clearCandidateTable() {
        try {
            Statement stmt = connection.createStatement();
            stmt.execute("SET FOREIGN_KEY_CHECKS=0");

            String sql = "TRUNCATE TABLE candidate";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static LinkedList<Candidate> getAllCandidates(int electionID) {
        LinkedList<Candidate> candidates = new LinkedList<>();
        try {
            // Execute a SQL query to retrieve the candidates for a given election ID
            String sql = "SELECT * FROM candidate WHERE electionID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, electionID);
            ResultSet result = statement.executeQuery();

            // Iterate through the ResultSet and create a new Candidate object for each row
            while (result.next()) {
                int candidateID = result.getInt("ID");
                String name = result.getString("name");
                int votesReceived = result.getInt("votesReceived");
                String party = result.getString("party");
                Candidate candidate = new Candidate(candidateID, name, party);
                candidate.setVotesReceived(votesReceived);
                candidates.add(candidate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return candidates;
    }
    public static LinkedList<Voter> getAllVoters(int electionID) {
        LinkedList<Voter> voterLinkedList = new LinkedList<>();
        try {
            String sql = "SELECT * FROM voter WHERE electionID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, electionID);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                int ID = result.getInt("ID");
                String name = result.getString("name");
                String address = result.getString("address");
                String password = result.getString("password");
                boolean hasVoted = result.getBoolean("hasVoted");
                boolean pending = result.getBoolean("pending");
                Voter voter = new Voter(ID, name, address, hasVoted, password, pending);
                for (Election election1 : globals.electionsLinkedList) {
                    if (election1.getID() == result.getInt("electionID")) {
                        voter.setElection(election1);
                    }
                }
                voterLinkedList.add(voter);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return voterLinkedList;
    }
    public static LinkedList<Vote> getAllVotes(int electionID) {
        LinkedList<Vote> voteLinkedList = new LinkedList<>();
        try {
            String sql = "SELECT * FROM vote WHERE electionID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, electionID);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                int voteID = result.getInt("ID");
                int voterID = result.getInt("voterID");
                int candidateID = result.getInt("candidateID");
                LocalTime voteCastTime = result.getTime("voteCastTime").toLocalTime();
                Vote vote = new Vote(voteID, voterID, candidateID, voteCastTime);
                for (Election election1 : globals.electionsLinkedList) {
                    if (election1.getID() == result.getInt("electionID")) {
                        vote.setElection(election1);
                    }
                }
                voteLinkedList.add(vote);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return voteLinkedList;
    }
    public static LinkedList<Election> getAllElections() {
        LinkedList<Election> electionLinkedList = new LinkedList<>();
        try {
            String sql = "SELECT * FROM election";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                int electionID = result.getInt("ID");
                int totalVotes = result.getInt("total_votes");
                LocalDate electionDate = result.getDate("election_date").toLocalDate();
                Election election = new Election(electionID, totalVotes, electionDate);
                election.setVotesLinkedList(getAllVotes(electionID));
                election.setCandidateLinkedList(getAllCandidates(electionID));
                LinkedList<Voter> voterLinkedList = getAllVoters(electionID), pending = new LinkedList<>(), accepted = new LinkedList<>(), voted = new LinkedList<>();
                for (Voter voter : voterLinkedList) {
                    if (voter.isPending()) {
                        pending.add(voter);
                    } else if (voter.isHasVoted()) {
                        voted.add(voter);
                    } else {
                        accepted.add(voter);
                    }
                }
                election.setAppendingVoterLinkedList(pending);
                election.setConfirmedVoterLinkedList(accepted);
                election.setVotedVotersList(voted);
                electionLinkedList.add(election);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return electionLinkedList;
    }
    public static void importCandidateTable() {
        for (Election election : globals.electionsLinkedList) {
            try {
                PreparedStatement pstmt = connection.prepareStatement("INSERT INTO candidate (ID, name,party, votesReceived,electionID) VALUES (?, ?, ?, ?, ?)ON DUPLICATE KEY UPDATE name=?,party=?, votesReceived=?,electionID=?");
                for (Candidate candidate : election.candidateLinkedList) {
                    pstmt.setInt(1, candidate.getID());
                    pstmt.setString(2, candidate.get_name());
                    pstmt.setString(3, candidate.getParty());
                    pstmt.setInt(4, candidate.getVotesReceived());
                    pstmt.setInt(5, candidate.getElection().getID());
                    pstmt.setString(6, candidate.get_name());
                    pstmt.setString(7, candidate.getParty());
                    pstmt.setInt(8, candidate.getVotesReceived());
                    pstmt.setInt(9, candidate.getElection().getID());
                    pstmt.executeUpdate();
                }

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        }
    }
    public static void importVotersTable() {
        for (Election election : globals.electionsLinkedList) {
            try {
                PreparedStatement pstmt = connection.prepareStatement("INSERT INTO voter (ID, name,address, password,hasVoted,pending,electionID) VALUES (?, ?, ?, ?, ?,?,?) ON DUPLICATE KEY UPDATE name=?,address=?, password=?,hasVoted=?,pending=?,electionID=?");
                for (Voter voter : election.appendingVoterLinkedList) {
                    pstmt.setInt(1, voter.getID());
                    pstmt.setString(2, voter.get_name());
                    pstmt.setString(3, voter.getAddress());
                    pstmt.setString(4, voter.getPassword());
                    pstmt.setBoolean(5, voter.isHasVoted());
                    pstmt.setBoolean(6, voter.isPending());
                    pstmt.setInt(7, voter.getElection().getID());
                    pstmt.setString(8, voter.get_name());
                    pstmt.setString(9, voter.getAddress());
                    pstmt.setString(10, voter.getPassword());
                    pstmt.setBoolean(11, voter.isHasVoted());
                    pstmt.setBoolean(12, voter.isPending());
                    pstmt.setInt(13, voter.getElection().getID());

                    pstmt.executeUpdate();
                }
                for (Voter voter : election.confirmedVoterLinkedList) {
                    pstmt.setInt(1, voter.getID());
                    pstmt.setString(2, voter.get_name());
                    pstmt.setString(3, voter.getAddress());
                    pstmt.setString(4, voter.getPassword());
                    pstmt.setBoolean(5, voter.isHasVoted());
                    pstmt.setBoolean(6, voter.isPending());
                    pstmt.setInt(7, voter.getElection().getID());
                    pstmt.setString(8, voter.get_name());
                    pstmt.setString(9, voter.getAddress());
                    pstmt.setString(10, voter.getPassword());
                    pstmt.setBoolean(11, voter.isHasVoted());
                    pstmt.setBoolean(12, voter.isPending());
                    pstmt.setInt(13, voter.getElection().getID());


                    pstmt.executeUpdate();
                }
                for (Voter voter : election.votedVotersList) {
                    pstmt.setInt(1, voter.getID());
                    pstmt.setString(2, voter.get_name());
                    pstmt.setString(3, voter.getAddress());
                    pstmt.setString(4, voter.getPassword());
                    pstmt.setBoolean(5, voter.isHasVoted());
                    pstmt.setBoolean(6, voter.isPending());
                    pstmt.setInt(7, voter.getElection().getID());
                    pstmt.setString(8, voter.get_name());
                    pstmt.setString(9, voter.getAddress());
                    pstmt.setString(10, voter.getPassword());
                    pstmt.setBoolean(11, voter.isHasVoted());
                    pstmt.setBoolean(12, voter.isPending());
                    pstmt.setInt(13, voter.getElection().getID());

                    pstmt.executeUpdate();
                }

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    public static void importVoteTable() {
        for (Election election : globals.electionsLinkedList) {
            try {
                PreparedStatement pstmt = connection.prepareStatement("INSERT INTO vote (ID, voterID,candidateID, voteCastTime,electionID) VALUES (?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE voterID=?,candidateID=?, voteCastTime=?,electionID=?");
                for (Vote vote : election.votesLinkedList) {
                    pstmt.setInt(1, vote.getID());
                    pstmt.setInt(2, vote.getVoterID());
                    pstmt.setInt(3, vote.getCandidateID());
                    pstmt.setTime(4, java.sql.Time.valueOf(vote.getVoteCastTime()));
                    pstmt.setInt(5, vote.getElection().getID());
                    pstmt.setInt(6, vote.getVoterID());
                    pstmt.setInt(7, vote.getCandidateID());
                    pstmt.setTime(8, java.sql.Time.valueOf(vote.getVoteCastTime()));
                    pstmt.setInt(9, vote.getElection().getID());
                    pstmt.executeUpdate();
                }

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    public static void importElectionTable(){
        try {
            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO election (ID, total_votes,election_date) VALUES (?, ?, ?)ON DUPLICATE KEY UPDATE total_votes=?,election_date=?");
            for (Election election : globals.electionsLinkedList) {
                pstmt.setInt(1, election.getID());
                pstmt.setInt(2, election.getTotalVotes());
                java.sql.Date date = java.sql.Date.valueOf(election.getElectionDate());
                pstmt.setDate(3,date);
                pstmt.setInt(4, election.getTotalVotes());
                pstmt.setDate(5,date);
                pstmt.executeUpdate();
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateVotersDB(){
        clearVoterTable();
        importVotersTable();
    }
    public static void updateVotesDB(){
        clearVoteTable();
        importVoteTable();
    }
    public static void updateCandidateDB(){
        clearCandidateTable();
        importCandidateTable();
    }
    public static void updateElectionDB(){
        clearElectionsTable();
        importElectionTable();
    }
    public static void updateAll(){
        updateElectionDB();
        updateCandidateDB();
        updateVotersDB();
        updateVotesDB();
    }


}
