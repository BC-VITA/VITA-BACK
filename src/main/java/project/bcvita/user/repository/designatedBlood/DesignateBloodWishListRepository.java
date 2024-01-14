package project.bcvita.user.repository.designatedBlood;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.bcvita.user.entity.designatedBlood.DesignatedBloodWishList;

@Repository
public interface DesignateBloodWishListRepository extends JpaRepository<DesignatedBloodWishList, Long> {

}
