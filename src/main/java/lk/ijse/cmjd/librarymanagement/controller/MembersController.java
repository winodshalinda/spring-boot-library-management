package lk.ijse.cmjd.librarymanagement.controller;

import lk.ijse.cmjd.librarymanagement.dto.MemberDto;
import lk.ijse.cmjd.librarymanagement.exception.MemberNotFoundException;
import lk.ijse.cmjd.librarymanagement.service.MembersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/member")
public class MembersController {
private final MembersService membersService;
@Autowired
    public MembersController(MembersService membersService){
    this.membersService=membersService;
}

    @GetMapping
    public String healthTest() {
        return "hello";
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MemberDto> addMember(@RequestBody MemberDto dto) {
        MemberDto memberDto = membersService.saveMember(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(memberDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable String id) {
        try {
            membersService.deleteMember(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (MemberNotFoundException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PatchMapping
    public ResponseEntity<Void> updateMember(@RequestParam String memberId, @RequestBody MemberDto memberDTO) {
        try {
            membersService.updateMember(memberId, memberDTO);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (MemberNotFoundException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MemberDto> getSelectedMember(@PathVariable String memberId) {
        try {
            return new ResponseEntity<>(membersService.getMember(memberId), HttpStatus.OK);
        }catch (MemberNotFoundException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(value = "getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MemberDto>> getAllMembers() {
        return new ResponseEntity<>(membersService.getAllMember(), HttpStatus.OK);
}
}
