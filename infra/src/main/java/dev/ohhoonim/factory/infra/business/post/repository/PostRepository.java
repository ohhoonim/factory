package dev.ohhoonim.factory.infra.business.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.ohhoonim.factory.infra.business.post.repository.entity.PostEntity;

public interface PostRepository extends JpaRepository<PostEntity, Long> {

}
