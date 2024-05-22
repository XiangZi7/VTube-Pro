export interface userState {
    userInfo: user
}

export interface user {
    avatarPath?: string;
    email?: string;
    nickName?: string;
    phone?: string;
    token?: string;
    userId?: number;
}
