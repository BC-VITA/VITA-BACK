package project.bcvita.user.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.bcvita.user.entity.BloodHouse;
import project.bcvita.user.entity.BloodHouseReservation;
import project.bcvita.user.entity.DesignatedBloodWriteUser;

import java.util.List;
import java.util.Optional;

public interface BloodHouseRepository extends JpaRepository<BloodHouse, Long> {
    Optional<BloodHouse> findBloodHouseById(Long id);

    BloodHouse findByCenterName(String houseName);
    Optional<BloodHouse> findByArea(String area);
    Optional<BloodHouse> findByBloodHouseAddress(String bloodHouseAddress);
//
//    //1가지 경우의 수
//    //헌혈의집 지역
//    @Query("select me from BloodHouse me where me.area like %:area%")
//    List<BloodHouse> bloodHouseArea(@Param("area") String area);
//
//    //헌혈의 집 주소
//    @Query("select me from BloodHouse me where me.bloodHouseAddress like %:bloodHouseAddress%")
//    List<BloodHouse> bloodHouseAddress(@Param("bloodHouseAddress") String bloodHouseAddress);
//
//    //헌혈 종류 (전혈)
//    @Query("select me from BloodHouse me where me.wholeBlood like %:wholeBlood%")
//    List<BloodHouse> filterWholeBlood(@Param("wholeBlood") String wholeBlood);
//
//    //헌혈 종류 (혈장)
//    @Query("select me from BloodHouse me where me.plasma like %:plasma%")
//    List<BloodHouse> filterPlasma(@Param("plasma") String plasma);
//
//    //헌혈 종류 (혈소판)
//    @Query("select me from BloodHouse me where me.platelet like %:platelet%")
//    List<BloodHouse> filterPlatelet(@Param("platelet") String platelet);
//
//    //센터명
//    @Query("select me from BloodHouse me where me.centerName like %:centerName%")
//    List<BloodHouse> bloodHouseCenterName(@Param("centerName") String centerName);
//
//    //센터 전화번호
//    @Query("select me from BloodHouse me where me.bloodHousePhoneNumber like %:bloodHousePhoneNumber%")
//    List<BloodHouse> bloodHousePhoneNumber(@Param("bloodHousePhoneNumber") String bloodHousePhoneNumber);
//
//
//
//    //2가지 경우의 수
//    //지역 + 헌혈 종류(전혈)
//    @Query("select me from BloodHouse me where me.area like %:area% and me.wholeBlood like %:wholeBlood%")
//    List<BloodHouse> areaAndWholeBlood(@Param("area") String area, @Param("wholeBlood") String wholeBlood);
//
//    //지역 + 헌혈 종류(혈장)
//    @Query("select me from BloodHouse me where me.area like %:area% and me.plasma like %:plasma%")
//    List<BloodHouse> areaAndPlasma(@Param("area") String area, @Param("plasma") String plasma);
//
//    //지역 + 헌혈 종류(혈소판)
//    @Query("select me from BloodHouse me where me.area like %:area% and me.platelet like %:platelet%")
//    List<BloodHouse> areaAndPlatelet(@Param("area") String area, @Param("platelet") String platelet);
//
//    //지역 + 주소
//    @Query("select me from BloodHouse me where me.area like %:area% and me.bloodHouseAddress like %:bloodHouseAddress%")
//    List<BloodHouse> areaAndAddress(@Param("area") String area, @Param("bloodHouseAddress") String bloodHouseAddress);
//
//    //지역 + 센터명
//    @Query("select me from BloodHouse me where me.area like %:area% and me.centerName like %:centerName%")
//    List<BloodHouse> areaAndCenterName(@Param("area") String area, @Param("centerName") String centerName);
//
//    //전혈 + 혈장
//    @Query("select me from BloodHouse me where me.wholeBlood like %:wholeBlood% and me.plasma like %:plasma%")
//    List<BloodHouse> wholeBloodAndPlasma(@Param("wholeBlood") String wholeBlood, @Param("plasma") String plasma);
//
//    //전혈 + 혈소판
//    @Query("select me from BloodHouse me where me.wholeBlood like %:wholeBlood% and me.platelet like %:platelet%")
//    List<BloodHouse> wholeBloodAndPlatelet(@Param("wholeBlood") String wholeBlood, @Param("platelet") String platelet);
//
//    //혈장 + 혈소판
//    @Query("select me from BloodHouse me where me.plasma like %:plasma% and me.platelet like %:platelet%")
//    List<BloodHouse> plasmaAndPlatelet(@Param("plasma") String plasma, @Param("platelet") String platelet);
//
//    //전화번호 + 지역
//    @Query("select me from BloodHouse me where me.bloodHousePhoneNumber like %:bloodHousePhoneNumber% and me.area like %:area%")
//    List<BloodHouse> PhoneNumberAndArea(@Param("bloodHousePhoneNumber") String bloodHousePhoneNumber, @Param("area") String area);
//
//    //전화번호 + 센터명
//    @Query("select me from BloodHouse me where me.bloodHousePhoneNumber like %:bloodHousePhoneNumber% and me.centerName like %:centerName%")
//    List<BloodHouse> PhoneNumberAndCenterName(@Param("bloodHousePhoneNumber") String bloodHousePhoneNumber, @Param("centerName") String centerName);
//
//    //전화번호 + 주소
//    @Query("select me from BloodHouse me where me.bloodHousePhoneNumber like %:bloodHousePhoneNumber% and me.bloodHouseAddress like %:bloodHouseAddress%")
//    List<BloodHouse> PhoneNumberAndAddress(@Param("bloodHousePhoneNumber") String bloodHousePhoneNumber, @Param("bloodHouseAddress") String bloodHouseAddress);
//
//
//    //3가지 경우의 수
//    //전혈 + 혈장 + 혈소판
//    @Query("select me from BloodHouse me where me.wholeBlood like %:wholeBlood% and me.plasma like %:plasma% and me.platelet like %:platelet%")
//    List<BloodHouse> wholeBloodAndPlasmaAndPlatelet(@Param("wholeBlood") String wholeBlood, @Param("plasma") String plasma, @Param("platelet") String platelet);
//
//    //지역 + 전혈 + 주소
//    @Query("select me from BloodHouse me where me.wholeBlood like %:wholeBlood% and me.area like %:area% and me.bloodHouseAddress like %:bloodHouseAddress%")
//    List<BloodHouse> wholeBloodAreaAddress(@Param("wholeBlood") String wholeBlood, @Param("area") String area, @Param("bloodHouseAddress") String bloodHouseAddress);
//
//    //지역 + 혈장 + 주소
//    @Query("select me from BloodHouse me where me.plasma like %:plasma% and me.area like %:area% and me.bloodHouseAddress like %:bloodHouseAddress%")
//    List<BloodHouse> plasmaAreaAddress(@Param("plasma") String plasma, @Param("area") String area, @Param("bloodHouseAddress") String bloodHouseAddress);
//
//    //지역 + 혈소판 + 주소
//    @Query("select me from BloodHouse me where me.platelet like %:platelet% and me.area like %:area% and me.bloodHouseAddress like %:bloodHouseAddress%")
//    List<BloodHouse> plateletAreaAddress(@Param("platelet") String platelet, @Param("area") String area, @Param("bloodHouseAddress") String bloodHouseAddress);
//
//    //센터명 + 전혈 + 주소
//    @Query("select me from BloodHouse me where me.wholeBlood like %:wholeBlood% and me.centerName like %:centerName% and me.bloodHouseAddress like %:bloodHouseAddress%")
//    List<BloodHouse> wholeBloodCenterNameAddress(@Param("wholeBlood") String wholeBlood, @Param("centerName") String centerName, @Param("bloodHouseAddress") String bloodHouseAddress);
//
//    //센터명 + 혈장 + 주소
//    @Query("select me from BloodHouse me where me.plasma like %:plasma% and me.centerName like %:centerName% and me.bloodHouseAddress like %:bloodHouseAddress%")
//    List<BloodHouse> plasmaCenterNameAddress(@Param("plasma") String plasma, @Param("centerName") String centerName, @Param("bloodHouseAddress") String bloodHouseAddress);
//
//    //센터명 + 혈소판 + 주소
//    @Query("select me from BloodHouse me where me.platelet like %:platelet% and me.centerName like %:centerName% and me.bloodHouseAddress like %:bloodHouseAddress%")
//    List<BloodHouse> plateletCenterNameAddress(@Param("platelet") String platelet, @Param("centerName") String centerName, @Param("bloodHouseAddress") String bloodHouseAddress);
//
//    //지역 + 센터명 + 주소
//    @Query("select me from BloodHouse me where me.area like %:area% and me.centerName like %:centerName% and me.bloodHouseAddress like %:bloodHouseAddress%")
//    List<BloodHouse> areaCenterNameAddress(@Param("area") String area, @Param("centerName") String centerName, @Param("bloodHouseAddress") String bloodHouseAddress);
}
