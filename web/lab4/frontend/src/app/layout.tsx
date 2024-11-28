import type { Metadata } from "next";
import localFont from "next/font/local";
import {Providers} from "../store/provider";
import "./globals.css";
import {PersistGate} from "redux-persist/integration/react";
import {persistor, store} from "@/store/store";
import {Provider} from "react-redux";
import {PersistGates} from "@/store/persistGate";

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="en">
      <body>
        <Providers>
          <PersistGates>
            {children}
          </PersistGates>
        </Providers>
      </body>
    </html>
  );
}
