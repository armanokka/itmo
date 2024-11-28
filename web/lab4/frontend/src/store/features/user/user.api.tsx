import {createApi, fetchBaseQuery} from "@reduxjs/toolkit/query/react";
import {UserState} from "@/store/features/user/user.types";

export const userApi = createApi({
    reducerPath: 'api/auth',
    baseQuery: fetchBaseQuery({baseUrl: 'http://localhost:8080/api/'}),
    endpoints: build => ({
        authorize: build.mutation({
            query: (user: TelegramUserPayload) => ({
                url: "telegram-auth",
                method: "POST",
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(user),
            }),
        }),
    }),
});

export const { useAuthorizeMutation } = userApi;
