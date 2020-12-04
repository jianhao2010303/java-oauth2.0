/**
 * Copyright (C), 2015-2020, xuct.net
 * FileName: SuperEntity
 * Author:   xutao
 * Date:     2020/11/18 9:54
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package spring.cloud.security.oath.common.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author xutao
 * @create 2020/11/18
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
public class SuperEntity<T extends Model<T>> extends Model<T> {


    private static final long serialVersionUID = -6646867998157063301L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date createTime = new Date();

    public SuperEntity(Long id) {
        this.id = id;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SuperEntity{" +
                "id=" + id +
                ", createTime=" + createTime +
                "} ";
    }
}