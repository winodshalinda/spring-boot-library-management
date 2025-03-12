package lk.ijse.cmjd.librarymanagement.controller;

import lk.ijse.cmjd.librarymanagement.dto.LendingDto;
import lk.ijse.cmjd.librarymanagement.exception.BookNotFoundException;
import lk.ijse.cmjd.librarymanagement.exception.EnoughBookNotFoundException;
import lk.ijse.cmjd.librarymanagement.exception.LendingNotFoundException;
import lk.ijse.cmjd.librarymanagement.exception.MemberNotFoundException;
import lk.ijse.cmjd.librarymanagement.service.LendingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/lending")
@RequiredArgsConstructor
public class LendingController {
    private final LendingService lendingService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LendingDto> addLending(@RequestBody LendingDto lendingDto) {
        if (lendingDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            LendingDto addLendingDto = lendingService.addLending(lendingDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(addLendingDto);
        } catch (BookNotFoundException | MemberNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (EnoughBookNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{lendingId}")
    public ResponseEntity<Void> handOverLending(@PathVariable String lendingId) {
        if (lendingId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            lendingService.handOverLending(lendingId);
            return ResponseEntity.noContent().build();
        } catch (LendingNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{lendingId}")
    public ResponseEntity<Void> deleteLending(@PathVariable String lendingId) {
        try {
            lendingService.deleteLending(lendingId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (LendingNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{lendingId}")
    public ResponseEntity<LendingDto> getLending(@PathVariable String lendingId) {
        try {
            lendingService.getSpecificLending(lendingId);
            return ResponseEntity.ok().body(lendingService.getSpecificLending(lendingId));
        }catch (LendingNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<LendingDto>> getAllLending() {
        lendingService.getAllLendings();
        return ResponseEntity.ok(lendingService.getAllLendings());
    }
}
