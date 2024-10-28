import React from 'react'

export default function Table() {
  return (
    <section className="container mx-auto p-6 font-mono">
      <div className="w-full mb-8 overflow-hidden rounded-lg shadow-lg">
        <div className="w-full overflow-x-auto">
          <table className="w-full">
            <thead>
              <tr className="text-md font-semibold tracking-wide text-left text-gray-900 bg-gray-100 uppercase border-b border-gray-600">
                <th className="px-4 py-3">Name</th>
                <th className="px-4 py-3">Age</th>
                <th className="px-4 py-3">Status</th>
                <th className="px-4 py-3">Date</th>
              </tr>
            </thead>
            <tbody className="bg-white">
              {[
                {
                  name: "Sufyan",
                  role: "Developer",
                  age: 22,
                  status: "Acceptable",
                  statusColor: "green",
                  date: "6/4/2000",
                },
                {
                  name: "Stevens",
                  role: "Programmer",
                  age: 27,
                  status: "Pending",
                  statusColor: "orange",
                  date: "6/10/2020",
                },
                {
                  name: "Nora",
                  role: "Designer",
                  age: 17,
                  status: "Unacceptable",
                  statusColor: "red",
                  date: "6/10/2020",
                },
                {
                  name: "Ali",
                  role: "Programmer",
                  age: 23,
                  status: "Acceptable",
                  statusColor: "green",
                  date: "6/10/2020",
                },
                {
                  name: "Khalid",
                  role: "Designer",
                  age: 20,
                  status: "Pending",
                  statusColor: "orange",
                  date: "6/10/2020",
                },
                {
                  name: "Nasser",
                  role: "Pen Tester",
                  age: 29,
                  status: "Acceptable",
                  statusColor: "green",
                  date: "6/10/2020",
                },
                {
                  name: "Mohammed",
                  role: "Web Designer",
                  age: 38,
                  status: "Acceptable",
                  statusColor: "green",
                  date: "6/10/2020",
                },
                {
                  name: "Saad",
                  role: "Data",
                  age: 19,
                  status: "Acceptable",
                  statusColor: "green",
                  date: "6/10/2020",
                },
                {
                  name: "Sami",
                  role: "Developer",
                  age: 21,
                  status: "Acceptable",
                  statusColor: "green",
                  date: "6/10/2020",
                },
              ].map((user, index) => (
                <tr key={index} className="text-gray-700">
                  <td className="px-4 py-3 border">
                    <div className="flex items-center text-sm">
                      <div className="relative w-8 h-8 mr-3 rounded-full md:block">
                        <img
                          className="object-cover w-full h-full rounded-full"
                          src="https://images.pexels.com/photos/5212324/pexels-photo-5212324.jpeg?auto=compress&cs=tinysrgb&dpr=3&h=750&w=1260"
                          alt={`${user.name}`}
                          loading="lazy"
                        />
                        <div
                          className="absolute inset-0 rounded-full shadow-inner"
                          aria-hidden="true"
                        ></div>
                      </div>
                      <div>
                        <p className="font-semibold text-black">{user.name}</p>
                        <p className="text-xs text-gray-600">{user.role}</p>
                      </div>
                    </div>
                  </td>
                  <td className="px-4 py-3 text-ms font-semibold border">
                    {user.age}
                  </td>
                  <td className="px-4 py-3 text-xs border">
                    <span
                      className={`px-2 py-1 font-semibold leading-tight text-${user.statusColor}-700 bg-${user.statusColor}-100 rounded-sm`}
                    >
                      {user.status}
                    </span>
                  </td>
                  <td className="px-4 py-3 text-sm border">{user.date}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </section>
  )
}
