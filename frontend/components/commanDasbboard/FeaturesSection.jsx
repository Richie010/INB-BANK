import { GlobeAltIcon, LockClosedIcon, CurrencyDollarIcon, DocumentChartBarIcon, BanknotesIcon } from '@heroicons/react/20/solid'

const features = [
  {
    name: '24/7 Access',
    description:
      'No more waiting in lines. Access your account and manage your money whenever it suits you.',
    href: '#',
    icon: GlobeAltIcon,
  },
  {
    name: 'Secure Transactions',
    description:
      'Our advanced security protocols ensure your transactions are safe and your data is protected.',
    href: '#',
    icon: LockClosedIcon,
  },
  {
    name: 'Seamless Transfers',
    description:
      'Transfer funds between accounts, pay bills, and manage your payments effortlessly.',
    href: '#',
    icon: CurrencyDollarIcon,
  },
  {
    name: 'Account Management',
    description:
      'View your account balance, track your transactions, and monitor your spending with ease.',
    href: '#',
    icon: DocumentChartBarIcon,
  },
  {
    name: 'Investment Options',
    description:
      'Explore a range of investment opportunities and manage your portfolio online.',
    href: '#',
    icon: BanknotesIcon,
  },
]

export default function Exampleee() {
  return (
    <div className="bg-gray-900 py-24 sm:py-32">
      <div className="mx-auto max-w-7xl px-6 lg:px-8">
        <div className="mx-auto max-w-2xl lg:text-center">
          <h2 className="text-base font-semibold leading-7 text-indigo-400">Why Choose Net Banking?</h2>
          <p className="mt-2 text-3xl font-bold tracking-tight text-white sm:text-4xl">
            The Advantages of Online Banking
          </p>
          <p className="mt-6 text-lg leading-8 text-gray-300">
            Discover the benefits of net banking and how it simplifies your financial management.
          </p>
        </div>
        <div className="mx-auto mt-16 max-w-2xl sm:mt-20 lg:mt-24 lg:max-w-none">
          <dl className="grid max-w-xl grid-cols-1 gap-x-8 gap-y-16 lg:max-w-none lg:grid-cols-3">
            {features.map((feature) => (
              <div key={feature.name} className="flex flex-col">
                <dt className="flex items-center gap-x-3 text-base font-semibold leading-7 text-white">
                  <feature.icon aria-hidden="true" className="h-5 w-5 flex-none text-indigo-400" />
                  {feature.name}
                </dt>
                <dd className="mt-4 flex flex-auto flex-col text-base leading-7 text-gray-300">
                  <p className="flex-auto">{feature.description}</p>
                  <p className="mt-6">
                    <a href={feature.href} className="text-sm font-semibold leading-6 text-indigo-400">
                      Learn more <span aria-hidden="true">→</span>
                    </a>
                  </p>
                </dd>
              </div>
            ))}
          </dl>
        </div>
      </div>
    </div>
  )
}
