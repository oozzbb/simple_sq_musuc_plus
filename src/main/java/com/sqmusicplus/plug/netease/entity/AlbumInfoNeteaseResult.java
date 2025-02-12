package com.sqmusicplus.plug.netease.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * @Classname AlbumInfoNeteaseResult
 * @Description 专辑信息
 * @Version 1.0.0
 * @Date 2024/2/22 17:32
 * @Created by SQ
 */

public class AlbumInfoNeteaseResult {

    @JSONField(name = "code")
    private Long code;
    @JSONField(name = "songs")
    private List<SongsDTO> songs;
    @JSONField(name = "album")
    private AlbumDTO album;
    @JSONField(name = "resourceState")
    private Boolean resourceState;

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public List<SongsDTO> getSongs() {
        return songs;
    }

    public void setSongs(List<SongsDTO> songs) {
        this.songs = songs;
    }

    public AlbumDTO getAlbum() {
        return album;
    }

    public void setAlbum(AlbumDTO album) {
        this.album = album;
    }

    public Boolean getResourceState() {
        return resourceState;
    }

    public void setResourceState(Boolean resourceState) {
        this.resourceState = resourceState;
    }

    public static class AlbumDTO {
        @JSONField(name = "artist")
        private ArtistDTO artist;
        @JSONField(name = "description")
        private String description;
        @JSONField(name = "pic")
        private Long pic;
        @JSONField(name = "type")
        private String type;
        @JSONField(name = "picUrl")
        private String picUrl;
        @JSONField(name = "artists")
        private List<ArtistsDTO> artists;
        @JSONField(name = "briefDesc")
        private String briefDesc;
        @JSONField(name = "onSale")
        private Boolean onSale;

        @JSONField(name = "company")
        private String company;
        @JSONField(name = "id")
        private Long id;
        @JSONField(name = "picId")
        private Long picId;
        @JSONField(name = "info")
        private InfoDTO info;
        @JSONField(name = "publishTime")
        private Long publishTime;
        @JSONField(name = "commentThreadId")
        private String commentThreadId;
        @JSONField(name = "blurPicUrl")
        private String blurPicUrl;
        @JSONField(name = "tags")
        private String tags;
        @JSONField(name = "companyId")
        private Long companyId;
        @JSONField(name = "size")
        private Long size;
        @JSONField(name = "copyrightId")
        private Long copyrightId;
        @JSONField(name = "paid")
        private Boolean paid;
        @JSONField(name = "name")
        private String name;
        @JSONField(name = "subType")
        private String subType;
        @JSONField(name = "mark")
        private Long mark;
        @JSONField(name = "status")
        private Long status;

        public ArtistDTO getArtist() {
            return artist;
        }

        public void setArtist(ArtistDTO artist) {
            this.artist = artist;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Long getPic() {
            return pic;
        }

        public void setPic(Long pic) {
            this.pic = pic;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public List<ArtistsDTO> getArtists() {
            return artists;
        }

        public void setArtists(List<ArtistsDTO> artists) {
            this.artists = artists;
        }

        public String getBriefDesc() {
            return briefDesc;
        }

        public void setBriefDesc(String briefDesc) {
            this.briefDesc = briefDesc;
        }

        public Boolean getOnSale() {
            return onSale;
        }

        public void setOnSale(Boolean onSale) {
            this.onSale = onSale;
        }


        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Long getPicId() {
            return picId;
        }

        public void setPicId(Long picId) {
            this.picId = picId;
        }

        public InfoDTO getInfo() {
            return info;
        }

        public void setInfo(InfoDTO info) {
            this.info = info;
        }

        public Long getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(Long publishTime) {
            this.publishTime = publishTime;
        }

        public String getCommentThreadId() {
            return commentThreadId;
        }

        public void setCommentThreadId(String commentThreadId) {
            this.commentThreadId = commentThreadId;
        }

        public String getBlurPicUrl() {
            return blurPicUrl;
        }

        public void setBlurPicUrl(String blurPicUrl) {
            this.blurPicUrl = blurPicUrl;
        }

        public String getTags() {
            return tags;
        }

        public void setTags(String tags) {
            this.tags = tags;
        }

        public Long getCompanyId() {
            return companyId;
        }

        public void setCompanyId(Long companyId) {
            this.companyId = companyId;
        }

        public Long getSize() {
            return size;
        }

        public void setSize(Long size) {
            this.size = size;
        }

        public Long getCopyrightId() {
            return copyrightId;
        }

        public void setCopyrightId(Long copyrightId) {
            this.copyrightId = copyrightId;
        }


        public Boolean getPaid() {
            return paid;
        }

        public void setPaid(Boolean paid) {
            this.paid = paid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSubType() {
            return subType;
        }

        public void setSubType(String subType) {
            this.subType = subType;
        }

        public Long getMark() {
            return mark;
        }

        public void setMark(Long mark) {
            this.mark = mark;
        }

        public Long getStatus() {
            return status;
        }

        public void setStatus(Long status) {
            this.status = status;
        }

        public static class ArtistDTO {
            @JSONField(name = "img1v1Url")
            private String img1v1Url;
            @JSONField(name = "picId_str")
            private String picidStr;
            @JSONField(name = "musicSize")
            private Long musicSize;
            @JSONField(name = "img1v1Id_str")
            private String img1v1idStr;
            @JSONField(name = "img1v1Id")
            private Long img1v1Id;
            @JSONField(name = "followed")
            private Boolean followed;
            @JSONField(name = "albumSize")
            private Long albumSize;
            @JSONField(name = "picUrl")
            private String picUrl;
            @JSONField(name = "topicPerson")
            private Long topicPerson;
            @JSONField(name = "briefDesc")
            private String briefDesc;
            @JSONField(name = "name")
            private String name;
            @JSONField(name = "alias")
            private List<String> alias;
            @JSONField(name = "id")
            private Long id;
            @JSONField(name = "picId")
            private Long picId;
            @JSONField(name = "trans")
            private String trans;

            public String getImg1v1Url() {
                return img1v1Url;
            }

            public void setImg1v1Url(String img1v1Url) {
                this.img1v1Url = img1v1Url;
            }

            public String getPicidStr() {
                return picidStr;
            }

            public void setPicidStr(String picidStr) {
                this.picidStr = picidStr;
            }

            public Long getMusicSize() {
                return musicSize;
            }

            public void setMusicSize(Long musicSize) {
                this.musicSize = musicSize;
            }

            public String getImg1v1idStr() {
                return img1v1idStr;
            }

            public void setImg1v1idStr(String img1v1idStr) {
                this.img1v1idStr = img1v1idStr;
            }

            public Long getImg1v1Id() {
                return img1v1Id;
            }

            public void setImg1v1Id(Long img1v1Id) {
                this.img1v1Id = img1v1Id;
            }

            public Boolean getFollowed() {
                return followed;
            }

            public void setFollowed(Boolean followed) {
                this.followed = followed;
            }

            public Long getAlbumSize() {
                return albumSize;
            }

            public void setAlbumSize(Long albumSize) {
                this.albumSize = albumSize;
            }

            public String getPicUrl() {
                return picUrl;
            }

            public void setPicUrl(String picUrl) {
                this.picUrl = picUrl;
            }

            public Long getTopicPerson() {
                return topicPerson;
            }

            public void setTopicPerson(Long topicPerson) {
                this.topicPerson = topicPerson;
            }

            public String getBriefDesc() {
                return briefDesc;
            }

            public void setBriefDesc(String briefDesc) {
                this.briefDesc = briefDesc;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<String> getAlias() {
                return alias;
            }

            public void setAlias(List<String> alias) {
                this.alias = alias;
            }

            public Long getId() {
                return id;
            }

            public void setId(Long id) {
                this.id = id;
            }

            public Long getPicId() {
                return picId;
            }

            public void setPicId(Long picId) {
                this.picId = picId;
            }

            public String getTrans() {
                return trans;
            }

            public void setTrans(String trans) {
                this.trans = trans;
            }
        }

        public static class InfoDTO {
            @JSONField(name = "threadId")
            private String threadId;
            @JSONField(name = "shareCount")
            private Long shareCount;
            @JSONField(name = "resourceId")
            private Long resourceId;
            @JSONField(name = "commentThread")
            private CommentThreadDTO commentThread;
            @JSONField(name = "likedCount")
            private Long likedCount;
            @JSONField(name = "liked")
            private Boolean liked;
            @JSONField(name = "resourceType")
            private Long resourceType;
            @JSONField(name = "commentCount")
            private Long commentCount;

            public String getThreadId() {
                return threadId;
            }

            public void setThreadId(String threadId) {
                this.threadId = threadId;
            }

            public Long getShareCount() {
                return shareCount;
            }

            public void setShareCount(Long shareCount) {
                this.shareCount = shareCount;
            }

            public Long getResourceId() {
                return resourceId;
            }

            public void setResourceId(Long resourceId) {
                this.resourceId = resourceId;
            }

            public CommentThreadDTO getCommentThread() {
                return commentThread;
            }

            public void setCommentThread(CommentThreadDTO commentThread) {
                this.commentThread = commentThread;
            }

            public Long getLikedCount() {
                return likedCount;
            }

            public void setLikedCount(Long likedCount) {
                this.likedCount = likedCount;
            }

            public Boolean getLiked() {
                return liked;
            }

            public void setLiked(Boolean liked) {
                this.liked = liked;
            }

            public Long getResourceType() {
                return resourceType;
            }

            public void setResourceType(Long resourceType) {
                this.resourceType = resourceType;
            }

            public Long getCommentCount() {
                return commentCount;
            }

            public void setCommentCount(Long commentCount) {
                this.commentCount = commentCount;
            }

            public static class CommentThreadDTO {
                @JSONField(name = "shareCount")
                private Long shareCount;
                @JSONField(name = "resourceId")
                private Long resourceId;
                @JSONField(name = "hotCount")
                private Long hotCount;
                @JSONField(name = "resourceTitle")
                private String resourceTitle;
                @JSONField(name = "resourceOwnerId")
                private Long resourceOwnerId;
                @JSONField(name = "id")
                private String id;
                @JSONField(name = "likedCount")
                private Long likedCount;
                @JSONField(name = "resourceInfo")
                private ResourceInfoDTO resourceInfo;
                @JSONField(name = "resourceType")
                private Long resourceType;
                @JSONField(name = "commentCount")
                private Long commentCount;

                public Long getShareCount() {
                    return shareCount;
                }

                public void setShareCount(Long shareCount) {
                    this.shareCount = shareCount;
                }

                public Long getResourceId() {
                    return resourceId;
                }

                public void setResourceId(Long resourceId) {
                    this.resourceId = resourceId;
                }

                public Long getHotCount() {
                    return hotCount;
                }

                public void setHotCount(Long hotCount) {
                    this.hotCount = hotCount;
                }

                public String getResourceTitle() {
                    return resourceTitle;
                }

                public void setResourceTitle(String resourceTitle) {
                    this.resourceTitle = resourceTitle;
                }

                public Long getResourceOwnerId() {
                    return resourceOwnerId;
                }

                public void setResourceOwnerId(Long resourceOwnerId) {
                    this.resourceOwnerId = resourceOwnerId;
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public Long getLikedCount() {
                    return likedCount;
                }

                public void setLikedCount(Long likedCount) {
                    this.likedCount = likedCount;
                }

                public ResourceInfoDTO getResourceInfo() {
                    return resourceInfo;
                }

                public void setResourceInfo(ResourceInfoDTO resourceInfo) {
                    this.resourceInfo = resourceInfo;
                }

                public Long getResourceType() {
                    return resourceType;
                }

                public void setResourceType(Long resourceType) {
                    this.resourceType = resourceType;
                }

                public Long getCommentCount() {
                    return commentCount;
                }

                public void setCommentCount(Long commentCount) {
                    this.commentCount = commentCount;
                }

                public static class ResourceInfoDTO {
                    @JSONField(name = "imgUrl")
                    private String imgUrl;
                    @JSONField(name = "name")
                    private String name;
                    @JSONField(name = "id")
                    private Long id;
                    @JSONField(name = "userId")
                    private Long userId;

                    public String getImgUrl() {
                        return imgUrl;
                    }

                    public void setImgUrl(String imgUrl) {
                        this.imgUrl = imgUrl;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public Long getId() {
                        return id;
                    }

                    public void setId(Long id) {
                        this.id = id;
                    }

                    public Long getUserId() {
                        return userId;
                    }

                    public void setUserId(Long userId) {
                        this.userId = userId;
                    }
                }
            }
        }

        public static class ArtistsDTO {
            @JSONField(name = "img1v1Url")
            private String img1v1Url;
            @JSONField(name = "musicSize")
            private Long musicSize;
            @JSONField(name = "img1v1Id_str")
            private String img1v1idStr;
            @JSONField(name = "img1v1Id")
            private Long img1v1Id;
            @JSONField(name = "followed")
            private Boolean followed;
            @JSONField(name = "albumSize")
            private Long albumSize;
            @JSONField(name = "picUrl")
            private String picUrl;
            @JSONField(name = "topicPerson")
            private Long topicPerson;
            @JSONField(name = "briefDesc")
            private String briefDesc;
            @JSONField(name = "name")
            private String name;
            @JSONField(name = "id")
            private Long id;
            @JSONField(name = "picId")
            private Long picId;
            @JSONField(name = "trans")
            private String trans;

            public String getImg1v1Url() {
                return img1v1Url;
            }

            public void setImg1v1Url(String img1v1Url) {
                this.img1v1Url = img1v1Url;
            }

            public Long getMusicSize() {
                return musicSize;
            }

            public void setMusicSize(Long musicSize) {
                this.musicSize = musicSize;
            }

            public String getImg1v1idStr() {
                return img1v1idStr;
            }

            public void setImg1v1idStr(String img1v1idStr) {
                this.img1v1idStr = img1v1idStr;
            }

            public Long getImg1v1Id() {
                return img1v1Id;
            }

            public void setImg1v1Id(Long img1v1Id) {
                this.img1v1Id = img1v1Id;
            }

            public Boolean getFollowed() {
                return followed;
            }

            public void setFollowed(Boolean followed) {
                this.followed = followed;
            }

            public Long getAlbumSize() {
                return albumSize;
            }

            public void setAlbumSize(Long albumSize) {
                this.albumSize = albumSize;
            }

            public String getPicUrl() {
                return picUrl;
            }

            public void setPicUrl(String picUrl) {
                this.picUrl = picUrl;
            }

            public Long getTopicPerson() {
                return topicPerson;
            }

            public void setTopicPerson(Long topicPerson) {
                this.topicPerson = topicPerson;
            }

            public String getBriefDesc() {
                return briefDesc;
            }

            public void setBriefDesc(String briefDesc) {
                this.briefDesc = briefDesc;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }




            public Long getId() {
                return id;
            }

            public void setId(Long id) {
                this.id = id;
            }

            public Long getPicId() {
                return picId;
            }

            public void setPicId(Long picId) {
                this.picId = picId;
            }

            public String getTrans() {
                return trans;
            }

            public void setTrans(String trans) {
                this.trans = trans;
            }
        }
    }

    public static class SongsDTO {
        @JSONField(name = "no")
        private Long no;
        @JSONField(name = "fee")
        private Long fee;
        @JSONField(name = "privilege")
        private PrivilegeDTO privilege;
        @JSONField(name = "mst")
        private Long mst;
        @JSONField(name = "dt")
        private Long dt;
        @JSONField(name = "pst")
        private Long pst;
        @JSONField(name = "pop")
        private Long pop;
        @JSONField(name = "rtype")
        private Long rtype;
        @JSONField(name = "id")
        private Long id;
        @JSONField(name = "sq")
        private SqDTO sq;
        @JSONField(name = "st")
        private Long st;
        @JSONField(name = "cd")
        private String cd;
        @JSONField(name = "cf")
        private String cf;
        @JSONField(name = "h")
        private HDTO h;
        @JSONField(name = "mv")
        private Long mv;
        @JSONField(name = "al")
        private AlDTO al;
        @JSONField(name = "l")
        private LDTO l;
        @JSONField(name = "cp")
        private Long cp;
        @JSONField(name = "m")
        private MDTO m;
        @JSONField(name = "djId")
        private Long djId;


        @JSONField(name = "ar")
        private List<ArDTO> ar;
        @JSONField(name = "ftype")
        private Long ftype;
        @JSONField(name = "t")
        private Long t;
        @JSONField(name = "v")
        private Long v;
        @JSONField(name = "name")
        private String name;
        @JSONField(name = "hr")
        private HrDTO hr;

        public Long getNo() {
            return no;
        }

        public void setNo(Long no) {
            this.no = no;
        }

        public Long getFee() {
            return fee;
        }

        public void setFee(Long fee) {
            this.fee = fee;
        }

        public PrivilegeDTO getPrivilege() {
            return privilege;
        }

        public void setPrivilege(PrivilegeDTO privilege) {
            this.privilege = privilege;
        }

        public Long getMst() {
            return mst;
        }

        public void setMst(Long mst) {
            this.mst = mst;
        }

        public Long getDt() {
            return dt;
        }

        public void setDt(Long dt) {
            this.dt = dt;
        }

        public Long getPst() {
            return pst;
        }

        public void setPst(Long pst) {
            this.pst = pst;
        }

        public Long getPop() {
            return pop;
        }

        public void setPop(Long pop) {
            this.pop = pop;
        }

        public Long getRtype() {
            return rtype;
        }

        public void setRtype(Long rtype) {
            this.rtype = rtype;
        }


        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public SqDTO getSq() {
            return sq;
        }

        public void setSq(SqDTO sq) {
            this.sq = sq;
        }

        public Long getSt() {
            return st;
        }

        public void setSt(Long st) {
            this.st = st;
        }

        public String getCd() {
            return cd;
        }

        public void setCd(String cd) {
            this.cd = cd;
        }

        public String getCf() {
            return cf;
        }

        public void setCf(String cf) {
            this.cf = cf;
        }

        public HDTO getH() {
            return h;
        }

        public void setH(HDTO h) {
            this.h = h;
        }

        public Long getMv() {
            return mv;
        }

        public void setMv(Long mv) {
            this.mv = mv;
        }

        public AlDTO getAl() {
            return al;
        }

        public void setAl(AlDTO al) {
            this.al = al;
        }

        public LDTO getL() {
            return l;
        }

        public void setL(LDTO l) {
            this.l = l;
        }

        public Long getCp() {
            return cp;
        }

        public void setCp(Long cp) {
            this.cp = cp;
        }

        public MDTO getM() {
            return m;
        }

        public void setM(MDTO m) {
            this.m = m;
        }

        public Long getDjId() {
            return djId;
        }

        public void setDjId(Long djId) {
            this.djId = djId;
        }


        public List<ArDTO> getAr() {
            return ar;
        }

        public void setAr(List<ArDTO> ar) {
            this.ar = ar;
        }

        public Long getFtype() {
            return ftype;
        }

        public void setFtype(Long ftype) {
            this.ftype = ftype;
        }

        public Long getT() {
            return t;
        }

        public void setT(Long t) {
            this.t = t;
        }

        public Long getV() {
            return v;
        }

        public void setV(Long v) {
            this.v = v;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public HrDTO getHr() {
            return hr;
        }

        public void setHr(HrDTO hr) {
            this.hr = hr;
        }

        public static class PrivilegeDTO {
            @JSONField(name = "flag")
            private Long flag;
            @JSONField(name = "dlLevel")
            private String dlLevel;
            @JSONField(name = "subp")
            private Long subp;
            @JSONField(name = "fl")
            private Long fl;
            @JSONField(name = "fee")
            private Long fee;
            @JSONField(name = "dl")
            private Long dl;
            @JSONField(name = "plLevel")
            private String plLevel;
            @JSONField(name = "maxBrLevel")
            private String maxBrLevel;
            @JSONField(name = "rightSource")
            private Long rightSource;
            @JSONField(name = "maxbr")
            private Long maxbr;
            @JSONField(name = "id")
            private Long id;
            @JSONField(name = "sp")
            private Long sp;
            @JSONField(name = "payed")
            private Long payed;
            @JSONField(name = "st")
            private Long st;
            @JSONField(name = "chargeInfoList")
            private List<ChargeInfoListDTO> chargeInfoList;
            @JSONField(name = "freeTrialPrivilege")
            private FreeTrialPrivilegeDTO freeTrialPrivilege;
            @JSONField(name = "downloadMaxbr")
            private Long downloadMaxbr;
            @JSONField(name = "downloadMaxBrLevel")
            private String downloadMaxBrLevel;
            @JSONField(name = "cp")
            private Long cp;
            @JSONField(name = "preSell")
            private Boolean preSell;
            @JSONField(name = "playMaxBrLevel")
            private String playMaxBrLevel;
            @JSONField(name = "cs")
            private Boolean cs;
            @JSONField(name = "toast")
            private Boolean toast;
            @JSONField(name = "playMaxbr")
            private Long playMaxbr;
            @JSONField(name = "flLevel")
            private String flLevel;
            @JSONField(name = "pl")
            private Long pl;

            public Long getFlag() {
                return flag;
            }

            public void setFlag(Long flag) {
                this.flag = flag;
            }

            public String getDlLevel() {
                return dlLevel;
            }

            public void setDlLevel(String dlLevel) {
                this.dlLevel = dlLevel;
            }

            public Long getSubp() {
                return subp;
            }

            public void setSubp(Long subp) {
                this.subp = subp;
            }

            public Long getFl() {
                return fl;
            }

            public void setFl(Long fl) {
                this.fl = fl;
            }

            public Long getFee() {
                return fee;
            }

            public void setFee(Long fee) {
                this.fee = fee;
            }

            public Long getDl() {
                return dl;
            }

            public void setDl(Long dl) {
                this.dl = dl;
            }

            public String getPlLevel() {
                return plLevel;
            }

            public void setPlLevel(String plLevel) {
                this.plLevel = plLevel;
            }

            public String getMaxBrLevel() {
                return maxBrLevel;
            }

            public void setMaxBrLevel(String maxBrLevel) {
                this.maxBrLevel = maxBrLevel;
            }

            public Long getRightSource() {
                return rightSource;
            }

            public void setRightSource(Long rightSource) {
                this.rightSource = rightSource;
            }

            public Long getMaxbr() {
                return maxbr;
            }

            public void setMaxbr(Long maxbr) {
                this.maxbr = maxbr;
            }

            public Long getId() {
                return id;
            }

            public void setId(Long id) {
                this.id = id;
            }

            public Long getSp() {
                return sp;
            }

            public void setSp(Long sp) {
                this.sp = sp;
            }

            public Long getPayed() {
                return payed;
            }

            public void setPayed(Long payed) {
                this.payed = payed;
            }

            public Long getSt() {
                return st;
            }

            public void setSt(Long st) {
                this.st = st;
            }

            public List<ChargeInfoListDTO> getChargeInfoList() {
                return chargeInfoList;
            }

            public void setChargeInfoList(List<ChargeInfoListDTO> chargeInfoList) {
                this.chargeInfoList = chargeInfoList;
            }

            public FreeTrialPrivilegeDTO getFreeTrialPrivilege() {
                return freeTrialPrivilege;
            }

            public void setFreeTrialPrivilege(FreeTrialPrivilegeDTO freeTrialPrivilege) {
                this.freeTrialPrivilege = freeTrialPrivilege;
            }

            public Long getDownloadMaxbr() {
                return downloadMaxbr;
            }

            public void setDownloadMaxbr(Long downloadMaxbr) {
                this.downloadMaxbr = downloadMaxbr;
            }

            public String getDownloadMaxBrLevel() {
                return downloadMaxBrLevel;
            }

            public void setDownloadMaxBrLevel(String downloadMaxBrLevel) {
                this.downloadMaxBrLevel = downloadMaxBrLevel;
            }

            public Long getCp() {
                return cp;
            }

            public void setCp(Long cp) {
                this.cp = cp;
            }

            public Boolean getPreSell() {
                return preSell;
            }

            public void setPreSell(Boolean preSell) {
                this.preSell = preSell;
            }

            public String getPlayMaxBrLevel() {
                return playMaxBrLevel;
            }

            public void setPlayMaxBrLevel(String playMaxBrLevel) {
                this.playMaxBrLevel = playMaxBrLevel;
            }

            public Boolean getCs() {
                return cs;
            }

            public void setCs(Boolean cs) {
                this.cs = cs;
            }

            public Boolean getToast() {
                return toast;
            }

            public void setToast(Boolean toast) {
                this.toast = toast;
            }

            public Long getPlayMaxbr() {
                return playMaxbr;
            }

            public void setPlayMaxbr(Long playMaxbr) {
                this.playMaxbr = playMaxbr;
            }

            public String getFlLevel() {
                return flLevel;
            }

            public void setFlLevel(String flLevel) {
                this.flLevel = flLevel;
            }

            public Long getPl() {
                return pl;
            }

            public void setPl(Long pl) {
                this.pl = pl;
            }

            public static class FreeTrialPrivilegeDTO {
                @JSONField(name = "userConsumable")
                private Boolean userConsumable;
                @JSONField(name = "resConsumable")
                private Boolean resConsumable;

                public Boolean getUserConsumable() {
                    return userConsumable;
                }

                public void setUserConsumable(Boolean userConsumable) {
                    this.userConsumable = userConsumable;
                }

                public Boolean getResConsumable() {
                    return resConsumable;
                }

                public void setResConsumable(Boolean resConsumable) {
                    this.resConsumable = resConsumable;
                }
            }

            public static class ChargeInfoListDTO {
                @JSONField(name = "rate")
                private Long rate;
                @JSONField(name = "chargeType")
                private Long chargeType;

                public Long getRate() {
                    return rate;
                }

                public void setRate(Long rate) {
                    this.rate = rate;
                }

                public Long getChargeType() {
                    return chargeType;
                }

                public void setChargeType(Long chargeType) {
                    this.chargeType = chargeType;
                }
            }
        }

        public static class SqDTO {
            @JSONField(name = "br")
            private Long br;
            @JSONField(name = "fid")
            private Long fid;
            @JSONField(name = "size")
            private Long size;
            @JSONField(name = "vd")
            private Long vd;
            @JSONField(name = "sr")
            private Long sr;

            public Long getBr() {
                return br;
            }

            public void setBr(Long br) {
                this.br = br;
            }

            public Long getFid() {
                return fid;
            }

            public void setFid(Long fid) {
                this.fid = fid;
            }

            public Long getSize() {
                return size;
            }

            public void setSize(Long size) {
                this.size = size;
            }

            public Long getVd() {
                return vd;
            }

            public void setVd(Long vd) {
                this.vd = vd;
            }

            public Long getSr() {
                return sr;
            }

            public void setSr(Long sr) {
                this.sr = sr;
            }
        }

        public static class HDTO {
            @JSONField(name = "br")
            private Long br;
            @JSONField(name = "fid")
            private Long fid;
            @JSONField(name = "size")
            private Long size;
            @JSONField(name = "vd")
            private Long vd;
            @JSONField(name = "sr")
            private Long sr;

            public Long getBr() {
                return br;
            }

            public void setBr(Long br) {
                this.br = br;
            }

            public Long getFid() {
                return fid;
            }

            public void setFid(Long fid) {
                this.fid = fid;
            }

            public Long getSize() {
                return size;
            }

            public void setSize(Long size) {
                this.size = size;
            }

            public Long getVd() {
                return vd;
            }

            public void setVd(Long vd) {
                this.vd = vd;
            }

            public Long getSr() {
                return sr;
            }

            public void setSr(Long sr) {
                this.sr = sr;
            }
        }

        public static class AlDTO {
            @JSONField(name = "picUrl")
            private String picUrl;
            @JSONField(name = "name")
            private String name;
            @JSONField(name = "pic_str")
            private String picStr;
            @JSONField(name = "id")
            private Long id;
            @JSONField(name = "pic")
            private Long pic;

            public String getPicUrl() {
                return picUrl;
            }

            public void setPicUrl(String picUrl) {
                this.picUrl = picUrl;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPicStr() {
                return picStr;
            }

            public void setPicStr(String picStr) {
                this.picStr = picStr;
            }

            public Long getId() {
                return id;
            }

            public void setId(Long id) {
                this.id = id;
            }

            public Long getPic() {
                return pic;
            }

            public void setPic(Long pic) {
                this.pic = pic;
            }
        }

        public static class LDTO {
            @JSONField(name = "br")
            private Long br;
            @JSONField(name = "fid")
            private Long fid;
            @JSONField(name = "size")
            private Long size;
            @JSONField(name = "vd")
            private Long vd;
            @JSONField(name = "sr")
            private Long sr;

            public Long getBr() {
                return br;
            }

            public void setBr(Long br) {
                this.br = br;
            }

            public Long getFid() {
                return fid;
            }

            public void setFid(Long fid) {
                this.fid = fid;
            }

            public Long getSize() {
                return size;
            }

            public void setSize(Long size) {
                this.size = size;
            }

            public Long getVd() {
                return vd;
            }

            public void setVd(Long vd) {
                this.vd = vd;
            }

            public Long getSr() {
                return sr;
            }

            public void setSr(Long sr) {
                this.sr = sr;
            }
        }

        public static class MDTO {
            @JSONField(name = "br")
            private Long br;
            @JSONField(name = "fid")
            private Long fid;
            @JSONField(name = "size")
            private Long size;
            @JSONField(name = "vd")
            private Long vd;
            @JSONField(name = "sr")
            private Long sr;

            public Long getBr() {
                return br;
            }

            public void setBr(Long br) {
                this.br = br;
            }

            public Long getFid() {
                return fid;
            }

            public void setFid(Long fid) {
                this.fid = fid;
            }

            public Long getSize() {
                return size;
            }

            public void setSize(Long size) {
                this.size = size;
            }

            public Long getVd() {
                return vd;
            }

            public void setVd(Long vd) {
                this.vd = vd;
            }

            public Long getSr() {
                return sr;
            }

            public void setSr(Long sr) {
                this.sr = sr;
            }
        }

        public static class HrDTO {
            @JSONField(name = "br")
            private Long br;
            @JSONField(name = "fid")
            private Long fid;
            @JSONField(name = "size")
            private Long size;
            @JSONField(name = "vd")
            private Long vd;
            @JSONField(name = "sr")
            private Long sr;

            public Long getBr() {
                return br;
            }

            public void setBr(Long br) {
                this.br = br;
            }

            public Long getFid() {
                return fid;
            }

            public void setFid(Long fid) {
                this.fid = fid;
            }

            public Long getSize() {
                return size;
            }

            public void setSize(Long size) {
                this.size = size;
            }

            public Long getVd() {
                return vd;
            }

            public void setVd(Long vd) {
                this.vd = vd;
            }

            public Long getSr() {
                return sr;
            }

            public void setSr(Long sr) {
                this.sr = sr;
            }
        }

        public static class ArDTO {
            @JSONField(name = "name")
            private String name;
            @JSONField(name = "id")
            private Long id;
            @JSONField(name = "alia")
            private List<String> alia;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public Long getId() {
                return id;
            }

            public void setId(Long id) {
                this.id = id;
            }

            public List<String> getAlia() {
                return alia;
            }

            public void setAlia(List<String> alia) {
                this.alia = alia;
            }
        }
    }
}
