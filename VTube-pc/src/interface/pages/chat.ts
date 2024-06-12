export interface State  {
    currentSessionList: ChatSession[]
    currentSessionIndex: number
}

export interface ChatSession {
    userId: string;
    nickName: string;
    avatarPath: string;
    lastMessage: string;
    lastMessageTime: string;
    chatId: string;
}

export interface ChatMessage {
    senderId: string;
    recipient: string;
    content: string;
    position: 'left' | 'right';
}