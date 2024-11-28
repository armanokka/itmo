interface TelegramUserPayload {
    id: number
    first_name: string
    last_name: string
    username: string
    photo_url: string
    auth_date: number
    hash: string
}


export interface UserState {
    firstName: string
    lastName: string
    username: string
    photoUrl: string
    authToken: string
}
