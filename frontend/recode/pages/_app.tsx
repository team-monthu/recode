import Layout from "@/components/Layout";
import { AppProps } from "next/app";
import "../styles/globals.css";

export default function Recode({ Component, pageProps }: AppProps) {
  return (
    <Layout>
      <Component {...pageProps} />
    </Layout>
  );
}
