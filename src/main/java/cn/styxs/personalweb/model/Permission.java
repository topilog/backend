package cn.styxs.personalweb.model;

import cn.styxs.personalweb.annotation.PermissionRequired;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.lang.reflect.Method;
import java.util.List;

@Entity
@Table(name = "permission")
@Data
@ToString(callSuper = true, exclude = {"belongRoles"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Permission extends BaseModel{
    @Column(unique = true, length = 128)
    private String name;
    @Column
    private String description;

    @ManyToMany(mappedBy = "permissions")
    @JsonIgnore
    private List<Role> belongRoles;

    // 根据类-方法信息生成权限名
    public static String getPermissionName(Method method, PermissionRequired permissionRequired) {
        String name = permissionRequired.permissionName();
        if ("".equals(name) || name == null) {
            StringBuilder stringBuilder = new StringBuilder();
            String fullName = method.getDeclaringClass().getName();
            int startIndex = fullName.substring(0, fullName.lastIndexOf(".")).lastIndexOf(".") + 1;
            stringBuilder.append(fullName.substring(startIndex));
            stringBuilder.append(".");
            stringBuilder.append(method.getName());
            name = stringBuilder.toString();
        }
        return name;
    }
}
