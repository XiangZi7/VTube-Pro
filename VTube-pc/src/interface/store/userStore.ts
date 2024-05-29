export interface userState {
    userInfo: user
}

export interface user {
    avatarPath?: string;
    userName?: string;
    email?: string;
    phone?: string;
    token?: string;
    userId?: number;
    realName?: string;
}
