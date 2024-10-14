package dev.ohhoonim.factory.businessRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.ohhoonim.factory.businessTable.Posts;


public interface PostRepository extends JpaRepository<Posts, Long> {

}
