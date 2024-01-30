package team6.epicenergyspa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team6.epicenergyspa.model.Province;

@Repository
public interface ProvinceDAO extends JpaRepository<Province,Long> {
    Province getProvincesByProvince(String provinceName);
}
