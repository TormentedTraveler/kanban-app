package com.esen.java_kanban_rework.service;

import com.esen.java_kanban_rework.dto.BoardDTO;
import com.esen.java_kanban_rework.dto.TaskDTO;
import com.esen.java_kanban_rework.entity.Board;
import com.esen.java_kanban_rework.entity.Task;
import com.esen.java_kanban_rework.entity.User;
import com.esen.java_kanban_rework.mappers.BoardMapper;
import com.esen.java_kanban_rework.mappers.TaskMapper;
import com.esen.java_kanban_rework.repository.BoardRepository;
import com.esen.java_kanban_rework.repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private final TaskRepository taskRepository;



    public BoardService(BoardRepository boardRepository, TaskRepository taskRepository) {
        this.boardRepository = boardRepository;
        this.taskRepository = taskRepository;
    }


    public List<BoardDTO> listBoardsForUser() {
        List<Board> boards = boardRepository.findAll();
        return BoardMapper.toDTOList(boards);
    }


    public void createBoard(BoardDTO board) {
        Board newBoard = Board.builder()
                .owner(User.builder().id(1L).build())
                .name(board.getName())
                .build();
        boardRepository.save(newBoard);
    }

    public Board getBoardById (Long id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Board not found with id " + id));
    }

    public List<TaskDTO> listTasksForBoard(Long boardId) {
        if (!boardRepository.existsById(boardId)) {
            throw new EntityNotFoundException("Board not found with id " + boardId);
        }
        List<Task> tasks = taskRepository.findAllByBoardId(boardId);
        return TaskMapper.toDTOList(tasks);
    }


    public void updateBoard(Long boardId, BoardDTO updatedBoardFromUser) {
        Board board = getBoardById(boardId);
        board.setName(updatedBoardFromUser.getName());
        boardRepository.save(board);
    }

    public void deleteBoard(Long boardId) {
        boardRepository.deleteById(boardId);
    }
}
