import React from 'react'

export default function Filter() {
  return (
    <>
        
        <div className="flex justify-center items-center pt-10">
  <div className="mb-4 flex flex-col gap-2 md:flex-row md:items-center">
    <select
      placeholder="status"
      className="px-4 py-2 border border-gray-300 rounded-md"
    >
      <option value="">No choose</option>
      <option value="">No choose</option>
    </select>
    <select
      placeholder="method"
      className="px-4 py-2 border border-gray-300 rounded-md"
    >
      <option value="">No choose</option>
      <option value="">No choose</option>
    </select>
    <input
      placeholder="startdate"
      type="date"
      className="px-4 py-2 border border-gray-300 rounded-md"
    />
    <input
      placeholder="enddate"
      type="date"
      className="px-4 py-2 border border-gray-300 rounded-md"
    />

    <button className="px-4 py-2 bg-blue-500 text-white rounded-md hover:bg-blue-600">
      Apply Filters
    </button>
    <button className="px-4 py-2 bg-gray-500 text-white rounded-md hover:bg-gray-600">
      Clear
    </button>
  </div>
</div>

        
    </>
  )
}
