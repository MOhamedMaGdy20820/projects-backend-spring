package com.example.springredditclone.mapper;


import
        com.example.springredditclone.dto.SubredditDto;
import com.example.springredditclone.model.Post;
import com.example.springredditclone.model.Subreddit;
import com.example.springredditclone.model.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.control.MappingControl;

import java.util.List;



@Mapper(componentModel = "spring")  // يسمح بحقن التبعية وميزات Spring الأخرى
public interface SubredditMapper {

    // maps a Subreddit object to a SubredditDto
    // transfer posts(model.subreddit) to numberOfPost(dto.subredditDto)
    @Mapping(target = "numberOfPosts", expression = "java(mapPosts(subreddit.getPosts()))")

    SubredditDto mapSubredditToDto(Subreddit subreddit);

    default Integer mapPosts(List<Post> numberOfPosts) {
        return numberOfPosts.size();
    }

    // inherit the mappings from mapSubredditToDto method in reverse
    @InheritInverseConfiguration
    // It also ignores mapping of the posts field from SubredditDto to Subreddit object.
    @Mapping(target = "posts", ignore = true )
    @Mapping(target = "user", source = "user")
    Subreddit mapDtoToSubreddit(SubredditDto subredditDto , User user);
}