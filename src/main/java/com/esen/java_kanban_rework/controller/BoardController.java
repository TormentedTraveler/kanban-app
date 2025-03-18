package com.esen.java_kanban_rework.controller;

import com.esen.java_kanban_rework.dto.BoardDTO;
import com.esen.java_kanban_rework.dto.TaskDTO;
import com.esen.java_kanban_rework.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/boards")
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

//    @GetMapping("/")
//    public List<Board> listBoards() {
//        return boardService.listBoards();
//    }

    @GetMapping("/")
    public ResponseEntity<List<BoardDTO>> listBoardsForUser() {
        return ResponseEntity.ok(boardService.listBoardsForUser());
    }

    @GetMapping("/{boardId}")
    public ResponseEntity<List<TaskDTO>> listTasksForBoard(@PathVariable("boardId") Long boardId) {
        return ResponseEntity.ok(boardService.listTasksForBoard(boardId));
    }

    @PostMapping("/")
    public ResponseEntity<Void> createBoard(@RequestBody BoardDTO board) {
        boardService.createBoard(board);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{boardId}") // to be done
    public ResponseEntity<Void> updateBoard(@PathVariable Long boardId, @RequestBody BoardDTO updatedBoard) {
        boardService.updateBoard(boardId, updatedBoard);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{boardId}") // to be done
    public ResponseEntity<Void> deleteBoard(@PathVariable Long boardId) {
        boardService.deleteBoard(boardId);
        return ResponseEntity.noContent().build();
    }

}