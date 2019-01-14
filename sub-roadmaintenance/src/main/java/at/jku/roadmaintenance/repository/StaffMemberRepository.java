package at.jku.roadmaintenance.repository;

import at.jku.roadmaintenance.StaffMember;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class StaffMemberRepository {
    private static StaffMemberRepository instance = null;

    public static StaffMemberRepository getInstance(){
        if(instance == null){
            instance = new StaffMemberRepository();

            instance.getStaffMembers()
                    .addAll(
                            Arrays.asList(
                                    new StaffMember("Christoph Burghuber"),
                                    new StaffMember("Gabriel Schedlberger"),
                                    new StaffMember("Peter Hager"),
                                    new StaffMember("Michael Rynkiewicz")
                            )
                    );
        }
        return instance;
    }

    private List<StaffMember> staffMembers = new LinkedList<>();

    public List<StaffMember> getStaffMembers() {
        return staffMembers;
    }
}
