export interface userState {
    userInfo: user
}

export interface user {
    avatarPath?: string;
    userName?: string;
    nickName?: string;
    email?: string;
    sex?: string;
    phone?: string;
    token?: string;
    userId?: number;
    realName?: string;
}
