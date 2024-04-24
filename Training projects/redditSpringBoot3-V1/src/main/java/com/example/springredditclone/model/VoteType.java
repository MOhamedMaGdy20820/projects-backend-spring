package com.example.springredditclone.model;
import com.example.springredditclone.exceptions.SpringRedditException;

import java.util.Arrays;

public enum VoteType {


    UPVOTE(1), DOWNVOTE(-1),
    ;

    private int direction;

    VoteType(int direction) {
    }

    public static VoteType lookup(Integer direction) {
        return Arrays.stream(VoteType.values())// بتجبلي كل القيم اللي انا حاتتطها لل VoteType
                .filter(value -> value.getDirection().equals(direction)) // بفلتر القيم كلها و اسيب اللي انا عايزه اللي موجود في ال direction
                .findAny()      // بعد الفلتره هل لسه في قيم تتساوي مع القيمه اللي انا عايزها
                .orElseThrow(() -> new SpringRedditException("Vote not found")); // لو مفيش هيرمي اكسبشن
    }

    public Integer getDirection() {
        return direction;
    }
}