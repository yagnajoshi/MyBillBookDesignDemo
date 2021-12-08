package com.yagna.billbook.utils;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;
/**
 * This class is responsible for the overlap effect of profile icons
 *
 * @author Yagna Joshi
 */
public class OverlapDecoration extends RecyclerView.ItemDecoration {

    @Override
    public void getItemOffsets(Rect outRect, @NotNull View view,
                               @NotNull RecyclerView parent,
                               @NotNull RecyclerView.State state) {

        outRect.set(0, 0, -35, 0);//<-- right
    }

}
