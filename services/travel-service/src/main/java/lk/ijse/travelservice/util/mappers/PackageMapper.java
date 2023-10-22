package lk.ijse.travelservice.util.mappers;

import lk.ijse.travelservice.dto.PackageDto;
import lk.ijse.travelservice.entity.Package;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PackageMapper {

    PackageMapper packageMapper = Mappers.getMapper(PackageMapper.class);

    default String arraysToString(String[] stringArray) {
        return String.join(",", stringArray);
    }

    default String[] stringToArray(String string) {
        return string.split(",");
    }

    Package toEntity(PackageDto dto);

    PackageDto toDto(Package entity);

    List<PackageDto> toDto(List<Package> entity);

    List<Package> toEntity(List<PackageDto> dto);
}
