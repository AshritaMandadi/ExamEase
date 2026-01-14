package service;

import model.Allocation;
import model.Room;
import model.Student;

public class AllocationService {

    public static void generateAllocation() {
        DataStore.allocations.clear();

        int studentIndex = 0;

        for (Room room : DataStore.rooms) {
            for (int seat = 1; seat <= room.getCapacity(); seat++) {
                if (studentIndex >= DataStore.students.size()) return;

                Student s = DataStore.students.get(studentIndex);

                DataStore.allocations.add(
                        new Allocation(
                                s.getRollNo(),
                                s.getName(),
                                room.getRoomNo(),
                                "Seat " + seat
                        )
                );

                studentIndex++;
            }
        }
    }
}

