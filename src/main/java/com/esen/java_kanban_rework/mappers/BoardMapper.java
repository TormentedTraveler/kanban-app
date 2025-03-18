package com.esen.java_kanban_rework.mappers;

import com.esen.java_kanban_rework.dto.BoardDTO;
import com.esen.java_kanban_rework.entity.Board;

import java.util.List;
import java.util.stream.Collectors;

public class BoardMapper {
    public static BoardDTO toDTO(Board board) {
        return new BoardDTO(board.getId(), board.getName());
    }

    public static List<BoardDTO> toDTOList(List<Board> boards) {
        return boards.stream()
                .map(BoardMapper::toDTO)
                .collect(Collectors.toList());
    }

}
