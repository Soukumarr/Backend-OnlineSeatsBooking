package com.example.OnlineSeatBook.service;

import com.example.OnlineSeatBook.dto.BookingDTO;
import com.example.OnlineSeatBook.dto.SeatDTO;
import com.example.OnlineSeatBook.model.Floor;
import com.example.OnlineSeatBook.model.Seat;
import com.example.OnlineSeatBook.repository.FloorRepository;
import com.example.OnlineSeatBook.repository.OfficeRepository;
import com.example.OnlineSeatBook.util.SortByIndex;
import com.example.OnlineSeatBook.util.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class LayoutService {

    @Autowired
    private FloorRepository floorRepository;

    @Autowired
    private OfficeRepository officeRepository;

    @Autowired
    BookingService bookingService;


// Simply get all seats on a floor in an Array
    public ArrayList<Seat> getAllSeats(Long floorId) {
        ArrayList<Seat> seatSection = null;
        Optional<Floor> seats = floorRepository.findById(floorId);

        if (seats.isPresent()){
            seatSection = new ArrayList<>(seats.get().getSeats());
            SortByIndex.sortByIndex(seatSection);
        }
        return seatSection;
    }


//    To get seats as Array of Arrays of Seats : 6 child arrays representing 6 sections
    public List<List<SeatDTO>> getFloorMap(Long floorId , LocalDate date) {
        List<List<SeatDTO>> seatMap = new ArrayList<>();
//        To make a list of nulls with a size in accordance with the layout sections
        for ( int i=1; i<=6; i++) {
            if (i == 2 || i ==5){
                SeatDTO[] seatArray = new SeatDTO[16];
                Arrays.fill(seatArray, null);
                ArrayList<SeatDTO> seats = new ArrayList<>(Arrays.asList(seatArray));
                seatMap.add(seats);
            }
            else{
                SeatDTO[] seatArray = new SeatDTO[24];
                Arrays.fill(seatArray, null);
                ArrayList<SeatDTO> seats = new ArrayList<>(Arrays.asList(seatArray));
                seatMap.add(seats);

        }}

        ArrayList<Seat> allSeats = getAllSeats(floorId);
        System.out.println(seatMap.toString());
//        Put each seat form the allSeats array in the appropriate section( 0 - 5 )
        for ( int i=0; i<6;i++) {
            for (Seat seat : allSeats){
                if(seat.getSection() == i+1) {
                    System.out.println(seat.toString());
                    SeatDTO seatDTO = SeatDTO.convertToDTO(seat);

//                   Get the status of seat and update in the SeatDTO
                    BookingDTO booking = bookingService.getBookingsBySeatIdAndDate(seat.getId(), date);
                    if (booking != null){
                    seatDTO.setStatus(booking.getStatus());
                    }else{
                        seatDTO.setStatus(Status.AVAILABLE);
                    }
                    seatMap.get(i).set( seat.getSeatIndex(), seatDTO);         // Use Set and not Add to replace null
//                    System.out.println(seatMap.get(j).add(seat));
                }
            }
            System.out.println("Size Of "+i+ " "+ seatMap.get(i).size());
        }
        return seatMap;
    }

    public List<List<SeatDTO>> getDefaultMap() {
        List<List<SeatDTO>> seatMap = new ArrayList<>();
//        To make a list of nulls with a size in accordance with the layout sections
        for ( int i=1; i<=6; i++) {
            if (i == 2 || i ==5){
                SeatDTO[] seatArray = new SeatDTO[16];
                Arrays.fill(seatArray, null);
                ArrayList<SeatDTO> seats = new ArrayList<>(Arrays.asList(seatArray));
                seatMap.add(seats);
            }
            else{
                SeatDTO[] seatArray = new SeatDTO[24];
                Arrays.fill(seatArray, null);
                ArrayList<SeatDTO> seats = new ArrayList<>(Arrays.asList(seatArray));
                seatMap.add(seats);

            }}
        return seatMap;
    }
}



