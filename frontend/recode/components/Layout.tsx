import Footer from "./Footer";
import Header from "./Header";

interface ILayoutProps {
  children: React.ReactNode;
}

export default function Layout({ children }: ILayoutProps) {
  return (
    <>
      <Header />
      <div className="bg-slate-200 h-screen">{children}</div>
      <Footer />
    </>
  );
}
