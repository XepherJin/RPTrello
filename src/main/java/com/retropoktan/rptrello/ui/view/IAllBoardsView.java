package com.retropoktan.rptrello.ui.view;

import com.retropoktan.rptrello.model.entity.Board;

import java.util.List;

/**
 * Created by RetroPoktan on 4/21/16.
 */
public interface IAllBoardsView extends IAdapterView {

    void showContent(List<Board> list);
    void seeBoardDetail(Board board);

    void showCreateBoard();

}
