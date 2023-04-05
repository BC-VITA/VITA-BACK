package project.bcvita.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.bcvita.user.entity.BloodHouse;
import project.bcvita.user.entity.DesignatedBloodWishList;

import java.util.Optional;

@Repository
public interface DesignateBloodWishListRepository extends JpaRepository<DesignatedBloodWishList, Long> {

}
