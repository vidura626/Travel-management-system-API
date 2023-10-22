package lk.ijse.travelservice.util.mappers;

import lk.ijse.travelservice.dto.PackageInfoDto;
import lk.ijse.travelservice.entity.PackageInfo;
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

    PackageInfo toEntity(PackageInfoDto dto);

    PackageInfoDto toDto(PackageInfo entity);

    List<PackageInfoDto> toDto(List<PackageInfo> entity);

    List<PackageInfo> toEntity(List<PackageInfoDto> dto);
}
