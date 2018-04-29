package com.zhyxcs.xxzz.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andSusercodeIsNull() {
            addCriterion("sUserCode is null");
            return (Criteria) this;
        }

        public Criteria andSusercodeIsNotNull() {
            addCriterion("sUserCode is not null");
            return (Criteria) this;
        }

        public Criteria andSusercodeEqualTo(String value) {
            addCriterion("sUserCode =", value, "susercode");
            return (Criteria) this;
        }

        public Criteria andSusercodeNotEqualTo(String value) {
            addCriterion("sUserCode <>", value, "susercode");
            return (Criteria) this;
        }

        public Criteria andSusercodeGreaterThan(String value) {
            addCriterion("sUserCode >", value, "susercode");
            return (Criteria) this;
        }

        public Criteria andSusercodeGreaterThanOrEqualTo(String value) {
            addCriterion("sUserCode >=", value, "susercode");
            return (Criteria) this;
        }

        public Criteria andSusercodeLessThan(String value) {
            addCriterion("sUserCode <", value, "susercode");
            return (Criteria) this;
        }

        public Criteria andSusercodeLessThanOrEqualTo(String value) {
            addCriterion("sUserCode <=", value, "susercode");
            return (Criteria) this;
        }

        public Criteria andSusercodeLike(String value) {
            addCriterion("sUserCode like", value, "susercode");
            return (Criteria) this;
        }

        public Criteria andSusercodeNotLike(String value) {
            addCriterion("sUserCode not like", value, "susercode");
            return (Criteria) this;
        }

        public Criteria andSusercodeIn(List<String> values) {
            addCriterion("sUserCode in", values, "susercode");
            return (Criteria) this;
        }

        public Criteria andSusercodeNotIn(List<String> values) {
            addCriterion("sUserCode not in", values, "susercode");
            return (Criteria) this;
        }

        public Criteria andSusercodeBetween(String value1, String value2) {
            addCriterion("sUserCode between", value1, value2, "susercode");
            return (Criteria) this;
        }

        public Criteria andSusercodeNotBetween(String value1, String value2) {
            addCriterion("sUserCode not between", value1, value2, "susercode");
            return (Criteria) this;
        }

        public Criteria andSbankcodeIsNull() {
            addCriterion("sBankCode is null");
            return (Criteria) this;
        }

        public Criteria andSbankcodeIsNotNull() {
            addCriterion("sBankCode is not null");
            return (Criteria) this;
        }

        public Criteria andSbankcodeEqualTo(String value) {
            addCriterion("sBankCode =", value, "sbankcode");
            return (Criteria) this;
        }

        public Criteria andSbankcodeNotEqualTo(String value) {
            addCriterion("sBankCode <>", value, "sbankcode");
            return (Criteria) this;
        }

        public Criteria andSbankcodeGreaterThan(String value) {
            addCriterion("sBankCode >", value, "sbankcode");
            return (Criteria) this;
        }

        public Criteria andSbankcodeGreaterThanOrEqualTo(String value) {
            addCriterion("sBankCode >=", value, "sbankcode");
            return (Criteria) this;
        }

        public Criteria andSbankcodeLessThan(String value) {
            addCriterion("sBankCode <", value, "sbankcode");
            return (Criteria) this;
        }

        public Criteria andSbankcodeLessThanOrEqualTo(String value) {
            addCriterion("sBankCode <=", value, "sbankcode");
            return (Criteria) this;
        }

        public Criteria andSbankcodeLike(String value) {
            addCriterion("sBankCode like", value, "sbankcode");
            return (Criteria) this;
        }

        public Criteria andSbankcodeNotLike(String value) {
            addCriterion("sBankCode not like", value, "sbankcode");
            return (Criteria) this;
        }

        public Criteria andSbankcodeIn(List<String> values) {
            addCriterion("sBankCode in", values, "sbankcode");
            return (Criteria) this;
        }

        public Criteria andSbankcodeNotIn(List<String> values) {
            addCriterion("sBankCode not in", values, "sbankcode");
            return (Criteria) this;
        }

        public Criteria andSbankcodeBetween(String value1, String value2) {
            addCriterion("sBankCode between", value1, value2, "sbankcode");
            return (Criteria) this;
        }

        public Criteria andSbankcodeNotBetween(String value1, String value2) {
            addCriterion("sBankCode not between", value1, value2, "sbankcode");
            return (Criteria) this;
        }

        public Criteria andSusernameIsNull() {
            addCriterion("sUserName is null");
            return (Criteria) this;
        }

        public Criteria andSusernameIsNotNull() {
            addCriterion("sUserName is not null");
            return (Criteria) this;
        }

        public Criteria andSusernameEqualTo(String value) {
            addCriterion("sUserName =", value, "susername");
            return (Criteria) this;
        }

        public Criteria andSusernameNotEqualTo(String value) {
            addCriterion("sUserName <>", value, "susername");
            return (Criteria) this;
        }

        public Criteria andSusernameGreaterThan(String value) {
            addCriterion("sUserName >", value, "susername");
            return (Criteria) this;
        }

        public Criteria andSusernameGreaterThanOrEqualTo(String value) {
            addCriterion("sUserName >=", value, "susername");
            return (Criteria) this;
        }

        public Criteria andSusernameLessThan(String value) {
            addCriterion("sUserName <", value, "susername");
            return (Criteria) this;
        }

        public Criteria andSusernameLessThanOrEqualTo(String value) {
            addCriterion("sUserName <=", value, "susername");
            return (Criteria) this;
        }

        public Criteria andSusernameLike(String value) {
            addCriterion("sUserName like", value, "susername");
            return (Criteria) this;
        }

        public Criteria andSusernameNotLike(String value) {
            addCriterion("sUserName not like", value, "susername");
            return (Criteria) this;
        }

        public Criteria andSusernameIn(List<String> values) {
            addCriterion("sUserName in", values, "susername");
            return (Criteria) this;
        }

        public Criteria andSusernameNotIn(List<String> values) {
            addCriterion("sUserName not in", values, "susername");
            return (Criteria) this;
        }

        public Criteria andSusernameBetween(String value1, String value2) {
            addCriterion("sUserName between", value1, value2, "susername");
            return (Criteria) this;
        }

        public Criteria andSusernameNotBetween(String value1, String value2) {
            addCriterion("sUserName not between", value1, value2, "susername");
            return (Criteria) this;
        }

        public Criteria andSpasswordIsNull() {
            addCriterion("sPassword is null");
            return (Criteria) this;
        }

        public Criteria andSpasswordIsNotNull() {
            addCriterion("sPassword is not null");
            return (Criteria) this;
        }

        public Criteria andSpasswordEqualTo(String value) {
            addCriterion("sPassword =", value, "spassword");
            return (Criteria) this;
        }

        public Criteria andSpasswordNotEqualTo(String value) {
            addCriterion("sPassword <>", value, "spassword");
            return (Criteria) this;
        }

        public Criteria andSpasswordGreaterThan(String value) {
            addCriterion("sPassword >", value, "spassword");
            return (Criteria) this;
        }

        public Criteria andSpasswordGreaterThanOrEqualTo(String value) {
            addCriterion("sPassword >=", value, "spassword");
            return (Criteria) this;
        }

        public Criteria andSpasswordLessThan(String value) {
            addCriterion("sPassword <", value, "spassword");
            return (Criteria) this;
        }

        public Criteria andSpasswordLessThanOrEqualTo(String value) {
            addCriterion("sPassword <=", value, "spassword");
            return (Criteria) this;
        }

        public Criteria andSpasswordLike(String value) {
            addCriterion("sPassword like", value, "spassword");
            return (Criteria) this;
        }

        public Criteria andSpasswordNotLike(String value) {
            addCriterion("sPassword not like", value, "spassword");
            return (Criteria) this;
        }

        public Criteria andSpasswordIn(List<String> values) {
            addCriterion("sPassword in", values, "spassword");
            return (Criteria) this;
        }

        public Criteria andSpasswordNotIn(List<String> values) {
            addCriterion("sPassword not in", values, "spassword");
            return (Criteria) this;
        }

        public Criteria andSpasswordBetween(String value1, String value2) {
            addCriterion("sPassword between", value1, value2, "spassword");
            return (Criteria) this;
        }

        public Criteria andSpasswordNotBetween(String value1, String value2) {
            addCriterion("sPassword not between", value1, value2, "spassword");
            return (Criteria) this;
        }

        public Criteria andSuserstateIsNull() {
            addCriterion("sUserState is null");
            return (Criteria) this;
        }

        public Criteria andSuserstateIsNotNull() {
            addCriterion("sUserState is not null");
            return (Criteria) this;
        }

        public Criteria andSuserstateEqualTo(String value) {
            addCriterion("sUserState =", value, "suserstate");
            return (Criteria) this;
        }

        public Criteria andSuserstateNotEqualTo(String value) {
            addCriterion("sUserState <>", value, "suserstate");
            return (Criteria) this;
        }

        public Criteria andSuserstateGreaterThan(String value) {
            addCriterion("sUserState >", value, "suserstate");
            return (Criteria) this;
        }

        public Criteria andSuserstateGreaterThanOrEqualTo(String value) {
            addCriterion("sUserState >=", value, "suserstate");
            return (Criteria) this;
        }

        public Criteria andSuserstateLessThan(String value) {
            addCriterion("sUserState <", value, "suserstate");
            return (Criteria) this;
        }

        public Criteria andSuserstateLessThanOrEqualTo(String value) {
            addCriterion("sUserState <=", value, "suserstate");
            return (Criteria) this;
        }

        public Criteria andSuserstateLike(String value) {
            addCriterion("sUserState like", value, "suserstate");
            return (Criteria) this;
        }

        public Criteria andSuserstateNotLike(String value) {
            addCriterion("sUserState not like", value, "suserstate");
            return (Criteria) this;
        }

        public Criteria andSuserstateIn(List<String> values) {
            addCriterion("sUserState in", values, "suserstate");
            return (Criteria) this;
        }

        public Criteria andSuserstateNotIn(List<String> values) {
            addCriterion("sUserState not in", values, "suserstate");
            return (Criteria) this;
        }

        public Criteria andSuserstateBetween(String value1, String value2) {
            addCriterion("sUserState between", value1, value2, "suserstate");
            return (Criteria) this;
        }

        public Criteria andSuserstateNotBetween(String value1, String value2) {
            addCriterion("sUserState not between", value1, value2, "suserstate");
            return (Criteria) this;
        }

        public Criteria andSuserlevelIsNull() {
            addCriterion("sUserLevel is null");
            return (Criteria) this;
        }

        public Criteria andSuserlevelIsNotNull() {
            addCriterion("sUserLevel is not null");
            return (Criteria) this;
        }

        public Criteria andSuserlevelEqualTo(String value) {
            addCriterion("sUserLevel =", value, "suserlevel");
            return (Criteria) this;
        }

        public Criteria andSuserlevelNotEqualTo(String value) {
            addCriterion("sUserLevel <>", value, "suserlevel");
            return (Criteria) this;
        }

        public Criteria andSuserlevelGreaterThan(String value) {
            addCriterion("sUserLevel >", value, "suserlevel");
            return (Criteria) this;
        }

        public Criteria andSuserlevelGreaterThanOrEqualTo(String value) {
            addCriterion("sUserLevel >=", value, "suserlevel");
            return (Criteria) this;
        }

        public Criteria andSuserlevelLessThan(String value) {
            addCriterion("sUserLevel <", value, "suserlevel");
            return (Criteria) this;
        }

        public Criteria andSuserlevelLessThanOrEqualTo(String value) {
            addCriterion("sUserLevel <=", value, "suserlevel");
            return (Criteria) this;
        }

        public Criteria andSuserlevelLike(String value) {
            addCriterion("sUserLevel like", value, "suserlevel");
            return (Criteria) this;
        }

        public Criteria andSuserlevelNotLike(String value) {
            addCriterion("sUserLevel not like", value, "suserlevel");
            return (Criteria) this;
        }

        public Criteria andSuserlevelIn(List<String> values) {
            addCriterion("sUserLevel in", values, "suserlevel");
            return (Criteria) this;
        }

        public Criteria andSuserlevelNotIn(List<String> values) {
            addCriterion("sUserLevel not in", values, "suserlevel");
            return (Criteria) this;
        }

        public Criteria andSuserlevelBetween(String value1, String value2) {
            addCriterion("sUserLevel between", value1, value2, "suserlevel");
            return (Criteria) this;
        }

        public Criteria andSuserlevelNotBetween(String value1, String value2) {
            addCriterion("sUserLevel not between", value1, value2, "suserlevel");
            return (Criteria) this;
        }

        public Criteria andSuserontimeIsNull() {
            addCriterion("sUserOnTime is null");
            return (Criteria) this;
        }

        public Criteria andSuserontimeIsNotNull() {
            addCriterion("sUserOnTime is not null");
            return (Criteria) this;
        }

        public Criteria andSuserontimeEqualTo(Date value) {
            addCriterion("sUserOnTime =", value, "suserontime");
            return (Criteria) this;
        }

        public Criteria andSuserontimeNotEqualTo(Date value) {
            addCriterion("sUserOnTime <>", value, "suserontime");
            return (Criteria) this;
        }

        public Criteria andSuserontimeGreaterThan(Date value) {
            addCriterion("sUserOnTime >", value, "suserontime");
            return (Criteria) this;
        }

        public Criteria andSuserontimeGreaterThanOrEqualTo(Date value) {
            addCriterion("sUserOnTime >=", value, "suserontime");
            return (Criteria) this;
        }

        public Criteria andSuserontimeLessThan(Date value) {
            addCriterion("sUserOnTime <", value, "suserontime");
            return (Criteria) this;
        }

        public Criteria andSuserontimeLessThanOrEqualTo(Date value) {
            addCriterion("sUserOnTime <=", value, "suserontime");
            return (Criteria) this;
        }

        public Criteria andSuserontimeIn(List<Date> values) {
            addCriterion("sUserOnTime in", values, "suserontime");
            return (Criteria) this;
        }

        public Criteria andSuserontimeNotIn(List<Date> values) {
            addCriterion("sUserOnTime not in", values, "suserontime");
            return (Criteria) this;
        }

        public Criteria andSuserontimeBetween(Date value1, Date value2) {
            addCriterion("sUserOnTime between", value1, value2, "suserontime");
            return (Criteria) this;
        }

        public Criteria andSuserontimeNotBetween(Date value1, Date value2) {
            addCriterion("sUserOnTime not between", value1, value2, "suserontime");
            return (Criteria) this;
        }

        public Criteria andSpwderrorIsNull() {
            addCriterion("sPWDError is null");
            return (Criteria) this;
        }

        public Criteria andSpwderrorIsNotNull() {
            addCriterion("sPWDError is not null");
            return (Criteria) this;
        }

        public Criteria andSpwderrorEqualTo(Byte value) {
            addCriterion("sPWDError =", value, "spwderror");
            return (Criteria) this;
        }

        public Criteria andSpwderrorNotEqualTo(Byte value) {
            addCriterion("sPWDError <>", value, "spwderror");
            return (Criteria) this;
        }

        public Criteria andSpwderrorGreaterThan(Byte value) {
            addCriterion("sPWDError >", value, "spwderror");
            return (Criteria) this;
        }

        public Criteria andSpwderrorGreaterThanOrEqualTo(Byte value) {
            addCriterion("sPWDError >=", value, "spwderror");
            return (Criteria) this;
        }

        public Criteria andSpwderrorLessThan(Byte value) {
            addCriterion("sPWDError <", value, "spwderror");
            return (Criteria) this;
        }

        public Criteria andSpwderrorLessThanOrEqualTo(Byte value) {
            addCriterion("sPWDError <=", value, "spwderror");
            return (Criteria) this;
        }

        public Criteria andSpwderrorIn(List<Byte> values) {
            addCriterion("sPWDError in", values, "spwderror");
            return (Criteria) this;
        }

        public Criteria andSpwderrorNotIn(List<Byte> values) {
            addCriterion("sPWDError not in", values, "spwderror");
            return (Criteria) this;
        }

        public Criteria andSpwderrorBetween(Byte value1, Byte value2) {
            addCriterion("sPWDError between", value1, value2, "spwderror");
            return (Criteria) this;
        }

        public Criteria andSpwderrorNotBetween(Byte value1, Byte value2) {
            addCriterion("sPWDError not between", value1, value2, "spwderror");
            return (Criteria) this;
        }

        public Criteria andStelephoneIsNull() {
            addCriterion("sTelephone is null");
            return (Criteria) this;
        }

        public Criteria andStelephoneIsNotNull() {
            addCriterion("sTelephone is not null");
            return (Criteria) this;
        }

        public Criteria andStelephoneEqualTo(String value) {
            addCriterion("sTelephone =", value, "stelephone");
            return (Criteria) this;
        }

        public Criteria andStelephoneNotEqualTo(String value) {
            addCriterion("sTelephone <>", value, "stelephone");
            return (Criteria) this;
        }

        public Criteria andStelephoneGreaterThan(String value) {
            addCriterion("sTelephone >", value, "stelephone");
            return (Criteria) this;
        }

        public Criteria andStelephoneGreaterThanOrEqualTo(String value) {
            addCriterion("sTelephone >=", value, "stelephone");
            return (Criteria) this;
        }

        public Criteria andStelephoneLessThan(String value) {
            addCriterion("sTelephone <", value, "stelephone");
            return (Criteria) this;
        }

        public Criteria andStelephoneLessThanOrEqualTo(String value) {
            addCriterion("sTelephone <=", value, "stelephone");
            return (Criteria) this;
        }

        public Criteria andStelephoneLike(String value) {
            addCriterion("sTelephone like", value, "stelephone");
            return (Criteria) this;
        }

        public Criteria andStelephoneNotLike(String value) {
            addCriterion("sTelephone not like", value, "stelephone");
            return (Criteria) this;
        }

        public Criteria andStelephoneIn(List<String> values) {
            addCriterion("sTelephone in", values, "stelephone");
            return (Criteria) this;
        }

        public Criteria andStelephoneNotIn(List<String> values) {
            addCriterion("sTelephone not in", values, "stelephone");
            return (Criteria) this;
        }

        public Criteria andStelephoneBetween(String value1, String value2) {
            addCriterion("sTelephone between", value1, value2, "stelephone");
            return (Criteria) this;
        }

        public Criteria andStelephoneNotBetween(String value1, String value2) {
            addCriterion("sTelephone not between", value1, value2, "stelephone");
            return (Criteria) this;
        }

        public Criteria andSemailIsNull() {
            addCriterion("sEmail is null");
            return (Criteria) this;
        }

        public Criteria andSemailIsNotNull() {
            addCriterion("sEmail is not null");
            return (Criteria) this;
        }

        public Criteria andSemailEqualTo(String value) {
            addCriterion("sEmail =", value, "semail");
            return (Criteria) this;
        }

        public Criteria andSemailNotEqualTo(String value) {
            addCriterion("sEmail <>", value, "semail");
            return (Criteria) this;
        }

        public Criteria andSemailGreaterThan(String value) {
            addCriterion("sEmail >", value, "semail");
            return (Criteria) this;
        }

        public Criteria andSemailGreaterThanOrEqualTo(String value) {
            addCriterion("sEmail >=", value, "semail");
            return (Criteria) this;
        }

        public Criteria andSemailLessThan(String value) {
            addCriterion("sEmail <", value, "semail");
            return (Criteria) this;
        }

        public Criteria andSemailLessThanOrEqualTo(String value) {
            addCriterion("sEmail <=", value, "semail");
            return (Criteria) this;
        }

        public Criteria andSemailLike(String value) {
            addCriterion("sEmail like", value, "semail");
            return (Criteria) this;
        }

        public Criteria andSemailNotLike(String value) {
            addCriterion("sEmail not like", value, "semail");
            return (Criteria) this;
        }

        public Criteria andSemailIn(List<String> values) {
            addCriterion("sEmail in", values, "semail");
            return (Criteria) this;
        }

        public Criteria andSemailNotIn(List<String> values) {
            addCriterion("sEmail not in", values, "semail");
            return (Criteria) this;
        }

        public Criteria andSemailBetween(String value1, String value2) {
            addCriterion("sEmail between", value1, value2, "semail");
            return (Criteria) this;
        }

        public Criteria andSemailNotBetween(String value1, String value2) {
            addCriterion("sEmail not between", value1, value2, "semail");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}