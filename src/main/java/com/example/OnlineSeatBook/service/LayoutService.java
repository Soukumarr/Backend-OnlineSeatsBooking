package com.example.OnlineSeatBook.service;

import com.example.OnlineSeatBook.model.Floor;
import com.example.OnlineSeatBook.model.Seat;
import com.example.OnlineSeatBook.repository.FloorRepository;
import com.example.OnlineSeatBook.repository.OfficeRepository;
import com.example.OnlineSeatBook.util.SortByIndex;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LayoutService {

    @Autowired
    private FloorRepository floorRepository;

    @Autowired
    private OfficeRepository officeRepository;



    public ArrayList<Seat> getSeatMap(Long floorId) {
        ArrayList<Seat> seatSection = null;
        Optional<Floor> seats = floorRepository.findById(floorId);

        if (seats.isPresent()){
            seatSection = new ArrayList<>(seats.get().getSeats());
            SortByIndex.sortByIndex(seatSection);
        }
        return seatSection;
    }

    public List<List<Seat>> getSectionMap(Long floorId) {
        List<List<Seat>> seatMap = new ArrayList<>();
        for ( int i=1; i<=6; i++) {
            if (i == 2 || i ==5){
                Seat[] seatArray = new Seat[16];
                Arrays.fill(seatArray, null);
                ArrayList<Seat> seats = new ArrayList<>(Arrays.asList(seatArray));
                seatMap.add(seats);
            }
            else{
                Seat[] seatArray = new Seat[24];
                Arrays.fill(seatArray, null);
                ArrayList<Seat> seats = new ArrayList<>(Arrays.asList(seatArray));
                seatMap.add(seats);

        }}
        ArrayList<Seat> allSeats = getSeatMap(floorId);
        System.out.println(seatMap.toString());
        for ( int i=0; i<6;i++) {
            for (Seat seat : allSeats){
                if(seat.getSection() == i+1) {
                    System.out.println(seat.toString());
                    seatMap.get(i).set( seat.getSeatIndex(), seat);         // Use Set and not Add to replace null
//                    System.out.println(seatMap.get(j).add(seat));
                }
            }
            System.out.println("Size Of "+i+ " "+ seatMap.get(i).size());
        }
        return seatMap;
    }
}



