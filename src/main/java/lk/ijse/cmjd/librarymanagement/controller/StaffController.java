package lk.ijse.cmjd.librarymanagement.controller;

import lk.ijse.cmjd.librarymanagement.dto.StaffDto;
import lk.ijse.cmjd.librarymanagement.exception.StaffNotFoundException;
import lk.ijse.cmjd.librarymanagement.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/staff")
public class StaffController {
    private final StaffService staffService;
    @Autowired
    public StaffController(StaffService staffService){
        this.staffService=staffService;
    }

    @GetMapping
    public String healthTest() {
        return "hello";
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StaffDto> addStaff(@RequestBody StaffDto dto) {
        StaffDto staffDto = staffService.saveStaff(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(staffDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteStaff(@PathVariable String id) {
        try {
            staffService.deleteStaff(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (StaffNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping
    public ResponseEntity<Void> updateStaff(@RequestParam String id, @RequestBody StaffDto dto) {
        try {
            staffService.updateStaff(id, dto);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (StaffNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StaffDto> getSelectedStaff(@PathVariable String id) {
        try {
            return new ResponseEntity<>(staffService.getStaff(id), HttpStatus.OK);
        }catch (StaffNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StaffDto>> getAllStaff() {

        return ResponseEntity.ok(staffService.getAllStaff());
    }
}
