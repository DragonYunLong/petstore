package com.yunlong.provider.mapper;

import com.yunlong.api.model.Profile;
import com.yunlong.api.model.ProfileExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileMapper {
    @SelectProvider(type=ProfileSqlProvider.class, method="countByExample")
    int countByExample(ProfileExample example);

    @DeleteProvider(type=ProfileSqlProvider.class, method="deleteByExample")
    int deleteByExample(ProfileExample example);

    @Delete({
        "delete from profile",
        "where userid = #{userid,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer userid);

    @Insert({
        "insert into profile (userid, lang, ",
        "catid)",
        "values (#{userid,jdbcType=INTEGER}, #{lang,jdbcType=VARCHAR}, ",
        "#{catid,jdbcType=INTEGER})"
    })
    int insert(Profile record);

    @InsertProvider(type=ProfileSqlProvider.class, method="insertSelective")
    int insertSelective(Profile record);

    @SelectProvider(type=ProfileSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="userid", property="userid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="lang", property="lang", jdbcType=JdbcType.VARCHAR),
        @Result(column="catid", property="catid", jdbcType=JdbcType.INTEGER)
    })
    List<Profile> selectByExample(ProfileExample example);

    @Select({
        "select",
        "userid, lang, catid",
        "from profile",
        "where userid = #{userid,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="userid", property="userid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="lang", property="lang", jdbcType=JdbcType.VARCHAR),
        @Result(column="catid", property="catid", jdbcType=JdbcType.INTEGER)
    })
    Profile selectByPrimaryKey(Integer userid);

    @UpdateProvider(type=ProfileSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Profile record, @Param("example") ProfileExample example);

    @UpdateProvider(type=ProfileSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Profile record, @Param("example") ProfileExample example);

    @UpdateProvider(type=ProfileSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Profile record);

    @Update({
        "update profile",
        "set lang = #{lang,jdbcType=VARCHAR},",
          "catid = #{catid,jdbcType=INTEGER}",
        "where userid = #{userid,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Profile record);
}