import Image from "next/image";

export default function Header() {
  return (
    <nav className="flex justify-around items-center w-full h-24">
      <div className="flex items-center border-black border-2 rounded-lg w-56 h-12 p-1 gap-1">
        <div className="flex justify-center items-center bg-slate-300 rounded-lg w-10 h-10 text-center">
          <Image src="/airplane.png" width="36" height="36" alt="tmp" />
        </div>
        <div className="flex justify-center items-center h-10 grow text-center">
          input
        </div>
      </div>
      <Image src="/logo.png" width="200" height="20" alt="logo" />
      <div className="flex justify-between items-center w-56 h-12 px-2">
        <div className="flex justify-center items-center bg-slate-300 rounded-lg w-12 h-12">
          <Image src="/airplane.png" width="36" height="36" alt="tmp" />
        </div>
        <div className="flex justify-center items-center bg-slate-300 rounded-lg w-12 h-12">
          <Image src="/airplane.png" width="36" height="36" alt="tmp" />
        </div>
        <div className="flex justify-center items-center bg-slate-300 rounded-lg w-12 h-12">
          <Image src="/airplane.png" width="36" height="36" alt="tmp" />
        </div>
        <div className="flex justify-center items-center bg-slate-300 rounded-lg w-12 h-12">
          <Image src="/airplane.png" width="36" height="36" alt="tmp" />
        </div>
      </div>
    </nav>
  );
}
