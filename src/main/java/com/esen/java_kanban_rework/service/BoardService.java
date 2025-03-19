package com.esen.java_kanban_rework.service;

import com.esen.java_kanban_rework.dto.BoardDTO;
import com.esen.java_kanban_rework.dto.TaskDTO;
import com.esen.java_kanban_rework.entity.Board;
import com.esen.java_kanban_rework.entity.Task;
import com.esen.java_kanban_rework.entity.User;
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
        List<BoardDTO> boardListGetResponse = new ArrayList<BoardDTO>();
        for (Board board: boards) {
            boardListGetResponse.add(BoardDTO.builder()
                    .id(board.getId())
                    .name(board.getName())
                    .build());
        }
        return boardListGetResponse;
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
        List <TaskDTO> taskListGetResponse = new ArrayList<TaskDTO>();
        for (Task task : tasks) {
            taskListGetResponse.add(TaskDTO.builder()
                    .boardId(task.getBoard().getId())
                    .title(task.getTitle())
                    .description(task.getDescription())
                    .id(task.getId())
                    .status(task.getStatus())
                    .build());
        }
        return taskListGetResponse;
    }


    public void updateBoard(Long boardId, BoardDTO updatedBoardFromUser) {
        Board board = getBoardById(boardId);
        board.setName(updatedBoardFromUser.getName());
        boardRepository.save(board);
    }

    public void deleteBoard(Long boardId) {
        Board board = getBoardById(boardId);
        boardRepository.deleteById(boardId);
    }
}
